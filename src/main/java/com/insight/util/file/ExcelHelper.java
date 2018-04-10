package com.insight.util.file;

import com.insight.util.file.excel.ColumnName;
import com.insight.util.file.excel.ExcelVer;
import com.insight.util.file.excel.FieldInfo;
import com.insight.util.DateHelper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.insight.util.file.excel.ColumnName.Policy;
import static com.insight.util.file.excel.ColumnName.Policy.Ignorable;
import static com.insight.util.file.excel.ColumnName.Policy.Required;

/**
 * @author 宣炳刚
 * @date 2017/11/8
 * @remark 通用Excel导入/导出帮助类
 */
public class ExcelHelper {

    /**
     * 日期格式
     */
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 工作簿
     */
    private Workbook workbook;

    /**
     * 当前Sheet列标题
     */
    private List<String> title;

    /**
     * 类型成员字段信息集合
     */
    private List<FieldInfo> fieldInfos;

    /**
     * 导出字段信息集合
     */
    private List<FieldInfo> exportFields;

    /**
     * 构造方法,用于导出2007版本Excel文件
     */
    public ExcelHelper() {
        this(ExcelVer.XLSX);
    }

    /**
     * 构造方法,用于导出Excel文件
     *
     * @param ver Excel文件版本
     */
    public ExcelHelper(ExcelVer ver) {
        switch (ver) {
            case XLS:
                workbook = new HSSFWorkbook();
                break;
            case XLSX:
                workbook = new XSSFWorkbook();
                break;
            default:
                workbook = null;
                break;
        }
    }

    /**
     * 构造方法,用于从文件导入数据
     *
     * @param file 输入Excel文件(.xls|.xlsx)的路径
     * @throws IOException
     */
    public ExcelHelper(String file) throws IOException {
        this(new FileInputStream(file));
    }

    /**
     * 构造方法,用于从文件导入数据
     *
     * @param data 输入字节流
     * @throws IOException
     */
    public ExcelHelper(byte[] data) throws IOException {
        this(new ByteArrayInputStream(data));
    }

    /**
     * 构造方法,用于从文件流导入数据
     *
     * @param stream 文件流
     * @throws IOException
     */
    public ExcelHelper(InputStream stream) throws IOException {
        try {
            workbook = new XSSFWorkbook(stream);
        } catch (Exception ex) {
            workbook = new HSSFWorkbook(stream);
        }
    }

    /**
     * 指定位置的Sheet是否存在
     *
     * @param sheetIndex Sheet位置
     * @return Sheet是否存在
     */
    public Boolean sheetIsExist(int sheetIndex) {
        return workbook.getSheetAt(sheetIndex) != null;
    }

    /**
     * 指定名称的Sheet是否存在
     *
     * @param sheetName Sheet名称
     * @return Sheet是否存在
     */
    public Boolean sheetIsExist(String sheetName) {
        return workbook.getSheet(sheetName) != null;
    }

    /**
     * 校验指定位置的Sheet是否包含关键列
     *
     * @param sheetIndex Sheet位置
     * @param keys       关键列名称(英文逗号分隔)
     * @return 是否通过校验
     */
    public Boolean verifyColumns(int sheetIndex, String keys) {
        String sheetName = workbook.getSheetName(sheetIndex);

        return verifyColumns(sheetName, keys);
    }

    /**
     * 校验指定名称的Sheet是否包含关键列
     *
     * @param sheetName Sheet名称
     * @param keys      关键列名称(英文逗号分隔)
     * @return 是否通过校验
     */
    public Boolean verifyColumns(String sheetName, String keys) {
        if (keys == null || keys.isEmpty()) {
            return true;
        }

        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            return false;
        }

        // 读取标题
        initTitel(sheet);
        if (title.isEmpty()) {
            return false;
        }

        // 读取关键列到集合并取标题集合的差集
        List<String> list = new ArrayList<>(Arrays.asList(keys.split(",")));
        list.removeAll(title);

        return list.isEmpty();
    }

    /**
     * 校验指定位置的Sheet是否包含关键列
     *
     * @param sheetIndex Sheet位置
     * @param type       类型
     * @return 是否通过校验
     */
    public Boolean verifyColumns(int sheetIndex, Class type) {
        String sheetName = workbook.getSheetName(sheetIndex);

        return verifyColumns(sheetName, type);
    }

    /**
     * 校验指定名称的Sheet是否包含关键列
     *
     * @param sheetName Sheet名称
     * @param type      类型
     * @return 是否通过校验
     */
    public Boolean verifyColumns(String sheetName, Class type) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null || type == null) {
            return false;
        }

        // 读取标题
        initTitel(sheet);
        if (title.isEmpty()) {
            return false;
        }

        // 读取关键列到集合并取标题集合的差集
        List<String> list = new ArrayList<>();
        Arrays.asList(type.getDeclaredFields()).forEach(i -> {
            if (i.isAnnotationPresent(ColumnName.class)) {
                Policy policy = i.getAnnotation(ColumnName.class).polic();
                if (policy.equals(Required)) {
                    list.add(i.getAnnotation(ColumnName.class).value());
                }
            }
        });
        list.removeAll(title);

        return list.isEmpty();
    }

    /**
     * 导出工作簿到Excel文件
     *
     * @param file 输出Excel文件(.xls|.xlsx)的路径及文件名
     * @throws IOException
     */
    public void exportFile(String file) throws IOException {
        if (workbook == null) {
            return;
        }

        OutputStream stream = new FileOutputStream(file);
        workbook.write(stream);
    }

    /**
     * 使用指定的数据集生成Sheet并导出工作簿到Excel文件
     *
     * @param list 输入数据集合
     * @param file 输出Excel文件(.xls|.xlsx)的路径及文件名
     * @param <T>  泛型参数
     * @throws IOException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public <T> void exportFile(String file, List<T> list) throws IOException, NoSuchFieldException, IllegalAccessException {
        exportFile(file, list, null);
    }

    /**
     * 使用指定的数据集生成指定名称的Sheet并导出工作簿到Excel文件
     *
     * @param list      输入数据集合
     * @param file      输出Excel文件(.xls|.xlsx)的路径及文件名
     * @param <T>       泛型参数
     * @param sheetName Sheet名称
     * @throws IOException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public <T> void exportFile(String file, List<T> list, String sheetName) throws IOException, NoSuchFieldException, IllegalAccessException {
        createSheet(list, sheetName);
        exportFile(file);
    }

    /**
     * 导出工作簿到数据流
     *
     * @return 文件路径
     * @throws IOException
     */
    public OutputStream exportStream() throws IOException {
        OutputStream stream = System.out;
        if (workbook != null) {
            workbook.write(stream);
        }

        return stream;
    }

    /**
     * 使用指定的数据集生成Sheet并导出工作簿到数据流
     *
     * @param list 输入数据集合
     * @param <T>  泛型参数
     * @return 文件流
     * @throws IOException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public <T> OutputStream exportStream(List<T> list) throws IOException, NoSuchFieldException, IllegalAccessException {
        return exportStream(list, null);
    }

    /**
     * 使用指定的数据集生成指定名称的Sheet并导出工作簿到数据流
     *
     * @param list      输入数据集合
     * @param <T>       泛型参数
     * @param sheetName Sheet名称
     * @return 文件流
     * @throws IOException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public <T> OutputStream exportStream(List<T> list, String sheetName) throws IOException, NoSuchFieldException, IllegalAccessException {
        createSheet(list, sheetName);

        return exportStream();
    }

    /**
     * 导入Excel文件中第一个Sheet的数据到指定类型的集合
     *
     * @param type 集合类型
     * @param <T>  泛型参数
     * @return 指定类型的集合
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <T> List<T> importSheet(Class<T> type) throws IllegalAccessException, InstantiationException, NoSuchFieldException, ParseException {
        return importSheet(0, type);
    }

    /**
     * 导入Excel文件中指定位置的Sheet的数据到指定类型的集合
     *
     * @param sheetIndex Sheet位置
     * @param type       集合类型
     * @param <T>        泛型参数
     * @return 指定类型的集合
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <T> List<T> importSheet(int sheetIndex, Class<T> type) throws IllegalAccessException, InstantiationException, NoSuchFieldException, ParseException {
        String name = workbook.getSheetName(sheetIndex);

        return importSheet(name, type);
    }

    /**
     * 导入Excel文件中指定名称的Sheet的数据到指定类型的集合
     *
     * @param sheetName Sheet名称
     * @param type      集合类型
     * @param <T>       泛型参数
     * @return 指定类型的集合
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <T> List<T> importSheet(String sheetName, Class<T> type) throws IllegalAccessException, InstantiationException, NoSuchFieldException, ParseException {
        Sheet sheet = workbook.getSheet(sheetName);

        return toList(sheet, type);
    }

    /**
     * 创建一个用于导入数据且指定Sheet名称的模板
     *
     * @param type 集合类型
     * @param <T>  泛型参数
     */
    public <T> void createTemplate(Class<T> type) {
        createTemplate(null, type);
    }

    /**
     * 创建一个用于导入数据且指定Sheet名称的模板
     *
     * @param sheetName Sheet名称
     * @param type      集合类型
     * @param <T>       泛型参数
     */
    public <T> void createTemplate(String sheetName, Class<T> type) {
        if (workbook == null) {
            return;
        }

        createTitel(sheetName, type);
    }

    /**
     * 使用输入的数据集在工作簿中创建一个Sheet
     *
     * @param list 输入数据集合
     * @param <T>  泛型参数
     */
    public <T> void createSheet(List<T> list) throws NoSuchFieldException, IllegalAccessException {
        createSheet(list, null);
    }

    /**
     * 使用输入的数据集在工作簿中创建一个指定名称的Sheet
     *
     * @param list      输入数据集合
     * @param sheetName Sheet名称
     * @param <T>       泛型参数
     */
    public <T> void createSheet(List<T> list, String sheetName) throws NoSuchFieldException, IllegalAccessException {
        if (workbook == null || list == null || list.isEmpty()) {
            return;
        }

        // 创建Sheet并生成标题行
        Class type = list.get(0).getClass();
        Sheet sheet = createTitel(sheetName, type);

        // 根据字段类型设置单元格格式并生成数据
        for (int i = 0; i < list.size(); i++) {
            Row row = sheet.createRow(i + 1);
            T item = list.get(i);
            if (item == null) {
                continue;
            }

            writeRow(row, item, type);
        }
    }

    /**
     * 创建指定名称的Sheet并生成标题行
     *
     * @param sheetName Sheet名称
     * @param type      集合类型
     * @return Sheet
     */
    private Sheet createTitel(String sheetName, Class type) {
        initFieldsInfo(type);
        if (sheetName == null || sheetName.isEmpty()) {
            sheetName = "Sheet" + (workbook.getNumberOfSheets() + 1);
        }

        Sheet sheet = workbook.createSheet(sheetName);
        Row row = sheet.createRow(0);
        for (int i = 0; i < exportFields.size(); i++) {
            FieldInfo info = exportFields.get(i);

            Cell cell = row.createCell(i, CellType.STRING);
            String columnName = info.getColumnName();
            cell.setCellValue(columnName == null ? info.getFiledName() : columnName);
        }

        return sheet;
    }

    /**
     * 写入数据对象字段值到行数据
     *
     * @param row  行数据
     * @param item 指定类型的数据对象
     * @param type 指定的类型
     * @param <T>  类型参数
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private <T> void writeRow(Row row, T item, Class type) throws NoSuchFieldException, IllegalAccessException {
        for (int i = 0; i < exportFields.size(); i++) {
            FieldInfo info = exportFields.get(i);
            CellType cellType = getCellType(info.getTypeName());
            Cell cell = row.createCell(i, cellType);

            Field field = type.getDeclaredField(info.getFiledName());
            field.setAccessible(true);
            Object value = field.get(item);
            if (value == null) {
                continue;
            }

            switch (info.getTypeName()) {
                case "Date":
                    SimpleDateFormat format = new SimpleDateFormat(info.getDateFormat());
                    cell.setCellValue(format.format(value));
                    break;
                default:
                    cell.setCellValue(value.toString());
                    break;
            }
        }
    }

    /**
     * 根据字段类型获取对应的单元格格式
     *
     * @param type 字段类型
     * @return 单元格格式
     */
    private CellType getCellType(String type) {
        switch (type) {
            case "int":
            case "Integer":
            case "long":
            case "Long":
            case "short":
            case "Short":
            case "byte":
            case "Byte":
            case "double":
            case "Double":
            case "float":
            case "Float":
            case "BigDecimal":
                return CellType.NUMERIC;
            case "boolean":
            case "Boolean":
                return CellType.BOOLEAN;
            default:
                return CellType.STRING;
        }
    }

    /**
     * 从Sheet导入数据到集合
     *
     * @param sheet Sheet
     * @param type  集合类型
     * @param <T>   泛型参数
     * @return 指定类型的集合
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private <T> List<T> toList(Sheet sheet, Class<T> type) throws IllegalAccessException, InstantiationException, NoSuchFieldException, ParseException {
        if (sheet == null || type == null) {
            return null;
        }

        List<T> table = new ArrayList<>();

        // 初始化字段信息字典和标题字典
        initFieldsInfo(type);
        initTitel(sheet);

        // 如标题为空,则返回一个空集合
        if (title.isEmpty()) {
            return table;
        }

        // 从第二行开始读取正文内容(第一行为标题行)
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            T item = readRow(row, type);
            table.add(item);
        }

        return table;
    }

    /**
     * 读取输入Row的数据到指定类型的对象实体
     *
     * @param row 输入的行数据
     * @return 指定类型的对象实体
     */
    private <T> T readRow(Row row, Class<T> type) throws NoSuchFieldException, IllegalAccessException, InstantiationException, ParseException {
        if (row == null) {
            return null;
        }

        // 顺序读取行内的每个单元格的数据并赋值给对应的字段
        T item = type.newInstance();
        Integer nullCount = 0;
        for (int i = 0; i < title.size(); i++) {
            String colName = title.get(i);

            // 如当前单元格所在列未在指定类型中定义,则跳过该单元格
            Optional<FieldInfo> optional = fieldInfos.stream().filter(f -> colName.equals(f.getColumnName()) || colName.equals(f.getFiledName())).findFirst();
            if (!optional.isPresent()) {
                nullCount++;
                continue;
            }

            // 选择单元格对应字段并初始化
            FieldInfo info = optional.get();
            String filedType = info.getTypeName();
            Field field = type.getDeclaredField(info.getFiledName());
            field.setAccessible(true);

            // 读取单元格数据,给字段赋值
            Cell cell = row.getCell(i);
            Object value = readCell(cell, filedType);
            if (value == null) {
                nullCount++;
            }

            setFieldValue(field, item, value);
        }

        return nullCount.equals(title.size()) ? null : item;
    }

    /**
     * 读取单元格数据
     *
     * @param cell 单元格
     * @param type 对应字段数据类型
     * @return 单元格数据
     */
    private Object readCell(Cell cell, String type) throws ParseException {
        if (cell == null) {
            return null;
        }

        switch (cell.getCellTypeEnum()) {
            case STRING:
                String value = cell.getStringCellValue();
                switch (type) {
                    case "Date":
                        return DateHelper.dateFormat(value);
                    case "boolean":
                    case "Boolean":
                        return Boolean.valueOf(value);
                    case "String":
                        return cell.getStringCellValue();
                    default:
                        Double val;
                        try {
                            val = Double.valueOf(value);
                        } catch (Exception ex) {
                            return null;
                        }

                        return numberFormat(val, type);
                }
            case NUMERIC:
                switch (type) {
                    case "Date":
                        return formatter.format(cell.getDateCellValue());
                    default:
                        return numberFormat(cell.getNumericCellValue(), type);
                }
            case FORMULA:
                switch (type) {
                    case "boolean":
                    case "Boolean":
                        return cell.getBooleanCellValue();
                    case "Date":
                        return cell.getDateCellValue();
                    case "String":
                        return cell.getStringCellValue();
                    default:
                        return numberFormat(cell.getNumericCellValue(), type);
                }
            case BOOLEAN:
                return cell.getBooleanCellValue();
            default:
                return null;
        }
    }

    /**
     * 设置字段的值
     *
     * @param field 字段
     * @param item  指定类型的数据对象
     * @param value 值
     * @param <T>   类型参数
     * @throws IllegalAccessException
     * @throws ParseException
     */
    private <T> void setFieldValue(Field field, T item, Object value) throws IllegalAccessException, ParseException {
        if (value == null) {
            return;
        }

        String val = value.toString();
        switch (field.getType().getSimpleName()) {
            case "long":
                field.setLong(item, Long.valueOf(val));
                break;
            case "int":
                field.setInt(item, Integer.valueOf(val));
                break;
            case "short":
                field.setShort(item, Short.valueOf(val));
                break;
            case "byte":
                field.setByte(item, Byte.valueOf(val));
                break;
            case "double":
                field.setDouble(item, Double.valueOf(val));
                break;
            case "float":
                field.setFloat(item, Float.valueOf(val));
                break;
            case "boolean":
                field.setBoolean(item, Boolean.valueOf(val));
                break;
            case "Integer":
                field.set(item, Integer.valueOf(val));
                break;
            case "Short":
                field.set(item, Short.valueOf(val));
                break;
            case "Byte":
                field.set(item, Byte.valueOf(val));
                break;
            case "Double":
                field.set(item, Double.valueOf(val));
                break;
            case "Float":
                field.set(item, Float.valueOf(val));
                break;
            case "Boolean":
                field.set(item, Boolean.valueOf(val));
                break;
            case "BigDecimal":
                field.set(item, BigDecimal.valueOf(Double.valueOf(val)));
                break;
            case "Date":
                field.set(item, formatter.parse(val));
                break;
            case "String":
                field.set(item, val);
                break;
            default:
                field.set(item, value);
                break;
        }
    }

    /**
     * 根据类型格式化数字
     *
     * @param value 输入数值
     * @param type  类型
     * @return 格式化的数字字符串
     */
    private String numberFormat(Double value, String type) {
        if (value == null) {
            return null;
        }

        NumberFormat numberFormat = NumberFormat.getInstance();
        switch (type) {
            case "int":
            case "Integer":
            case "long":
            case "Long":
            case "short":
            case "Short":
            case "byte":
            case "Byte":
            case "boolean":
            case "Boolean":
                numberFormat.setMaximumFractionDigits(0);
                break;
            case "double":
            case "Double":
            case "float":
            case "Float":
            case "BigDecimal":
            case "String":
                break;
            default:
                return null;
        }

        return numberFormat.format(value).replace(",", "");
    }

    /**
     * 读取标题,生成标题和对应的数据类型的字典
     *
     * @param sheet 数据表
     */
    private void initTitel(Sheet sheet) {
        title = new ArrayList<>();
        Row row = sheet.getRow(0);
        if (row == null) {
            return;
        }

        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            title.add(cell == null ? "" : cell.getStringCellValue());
        }
    }

    /**
     * 生成指定类型对应的字段信息集合
     *
     * @param type 指定的类型
     */
    private void initFieldsInfo(Class type) {
        fieldInfos = new ArrayList<>();
        List<Field> fields = Arrays.asList(type.getDeclaredFields());
        for (Field i : fields) {
            String typeName = i.getType().getSimpleName();
            FieldInfo info = new FieldInfo();
            info.setFiledName(i.getName());
            info.setTypeName(typeName);

            // 如读取到列名自定义注解
            if (i.isAnnotationPresent(ColumnName.class)) {
                ColumnName annotation = i.getAnnotation(ColumnName.class);
                info.setColumnName(annotation.value());
                info.setDateFormat("Date".equals(typeName) ? annotation.dateFormat() : null);
                info.setColumnPolicy(annotation.polic());
            }

            fieldInfos.add(info);
        }

        exportFields = fieldInfos.stream().filter(i -> !Ignorable.equals(i.getColumnPolicy())).collect(Collectors.toList());
    }
}
