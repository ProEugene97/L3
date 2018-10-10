import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.util.*;

public class Excel {

    public static String[] List = {"Имя", "Фамилия", "Отчество", "Возраст", "Пол", "Дата Рождения", "ИНН", "Почтовый индекс", "Страна", "Область", "Город", "Улица", "Дом", "Квартира"};

    public static void main(String[] args) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Новый лист");
        int rowNum = 0;
        Row row = sheet.createRow(rowNum);
        for (int i = 0; i != List.length; ++i) {
            row.createCell(i).setCellValue(List[i]);
        }
        int size = 1 + (int) (Math.random()*30);
        List<Data> dataList = fillData(size);
        for (Data data : dataList) {
            createSheetHeader(sheet, ++rowNum, data);
        }
        File file = new File("File.xls");
        try {
            System.setErr(new PrintStream(new File("log.txt")));
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
        } catch (Exception e) {
                e.printStackTrace();
        }
        System.err.println("Файл создан:" + file.getAbsolutePath().toString());
    }

    private static int randBetween(int start, int end){
        return start + (int) Math.round(Math.random()*(end - start));
    }

    private static String read_file(String path, int line_number) {
        String fileContent = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
            String sub;
            int i = 1;
            while ((sub = br.readLine()) != null) {
                if(i == line_number) {
                    fileContent = sub;
                    break;
                }
                ++i;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

    private static void write_name(Data data) {
        int number = randBetween(1, 30);
        if (randBetween(0, 1) == 1) {
            String name = read_file("src/main/resources/MaleName.txt", number);
            data.set_name(name);
            data.set_sex("М");
        } else {
            String name = read_file("src/main/resources/FemaleName.txt", number);
            data.set_name(name);
            data.set_sex("Ж");
        }
    }

    private static void write_surname(Data data) {
        int number = randBetween(1, 30);
        if (data.get_sex() == "М") {
            String surname = read_file("src/main/resources/MaleSurname.txt", number);
            data.set_surname(surname);
        } else {
            String surname = read_file("src/main/resources/FemaleSurname.txt", number);
            data.set_surname(surname);
        }
    }

    private static void write_patronymic(Data data){
        int number = randBetween(1, 30);
        if (data.get_sex() == "М") {
            String patronymic = read_file("src/main/resources/MalePatronymic.txt", number);
            data.set_patronymic(patronymic);
        } else {
            String patronymic = read_file("src/main/resources/FemalePatronymic.txt", number);
            data.set_patronymic(patronymic);
        }
    }

    private static void write_country(Data data) {
        int number = randBetween(1, 30);
        String country = read_file("src/main/resources/Countries.txt", number);
        data.set_country(country);
    }
    private static void write_city(Data data) {
        int number = randBetween(1, 30);
        String city = read_file("src/main/resources/Cities.txt", number);
        data.set_city(city);
    }

    private static void write_street(Data data) {
        int number = randBetween(1, 30);
        String street = read_file("src/main/resources/Streets.txt", number);
        data.set_street(street);
    }

    private static void write_region(Data data) {
        int number = randBetween(1, 30);
        String region = read_file("src/main/resources/Regions.txt", number);
        data.set_region(region);
    }

    private static void write_dob(Data data) {
        GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(1920, 2000);
        gc.set(gc.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
        gc.set(gc.DAY_OF_MONTH, dayOfYear);
        data.set_dob(gc);
    }

    //вычисление возраста
    private static void write_age(Data data) {
        Calendar calendar = Calendar.getInstance();
        int age = calendar.get(Calendar.YEAR) - data.get_dob().get(data.get_dob().YEAR);
        if (((data.get_dob().get(data.get_dob().MONTH)) - calendar.get(Calendar.MONTH) >= 0) & (((data.get_dob().get(data.get_dob().DAY_OF_MONTH)) - calendar.get(Calendar.DAY_OF_MONTH) >= 0))) {
            data.set_age(age - 1);
        } else data.set_age(age);
    }

    private static void write_postcode(Data data) {
        int number = randBetween(1000000, 10000000-1);
        data.set_postcode(number);
    }

    private static void write_apartment(Data data) {
        int number = randBetween(1, 1000);
        data.set_apartment(number);
    }

    private static void write_house(Data data) {
        int number = randBetween(1, 100);
        data.set_house(number);
    }

    //генерация ИНН
    private static void write_itn(Data data) {
        int branch_number = randBetween(10, 51);
        Long itn = 770000000000L + branch_number*100000000L;
        int k = 100;
        for (int i = 1; i <= 6; ++i) {
            int n = randBetween(0, 9);
            itn += n*k;
            k *= 10;
        }
        int[] array = new int[10];
        k = 1;
        for (int i = 0; i != 10; ++i) {
            array[i] = (int)(itn/100) % (10*k);
            k *= 10;
        }
        // коэфиценты для второго контрольного числа
        int [] coeficents2 = {7, 2, 4, 10, 3, 5, 9, 4, 6, 8};
        int sum2 = 0;
        for (int i = 0; i != 10; ++i) {
            sum2 = coeficents2[i] * array[i];
        }
        int num_kontrol2 = sum2 % 11;
        itn += num_kontrol2*10;
        // коэфиценты для первого контрольного числа
        int [] coeficents1 = {3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8};
        int sum1 = 0;
        for (int i = 0; i != 10; ++i) {
            sum1 = coeficents1[i] * array[i];
        }
        sum1 = coeficents1[10] * num_kontrol2;
        int num_kontrol1 = sum1 % 11;
        itn += num_kontrol1;
        data.set_itn(itn);
    }

    private static List<Data> fillData(int size) {
        List<Data> data = new ArrayList<>();
        for (int i = 0; i != size; ++i) {
            data.add(new Data());
            write_name(data.get(i));
            write_surname(data.get(i));
            write_patronymic(data.get(i));
            write_country(data.get(i));
            write_region(data.get(i));
            write_city(data.get(i));
            write_street(data.get(i));
            write_dob(data.get(i));
            write_age(data.get(i));
            write_postcode(data.get(i));
            write_apartment(data.get(i));
            write_house(data.get(i));
            write_itn(data.get(i));
        }
        return data;
    }

    private static void createSheetHeader(HSSFSheet sheet, int rowNum, Data data) {
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue(data.get_name());
        row.createCell(1).setCellValue(data.get_surname());
        row.createCell(2).setCellValue(data.get_patronymic());
        row.createCell(3).setCellValue(data.get_age());
        row.createCell(4).setCellValue(data.get_sex());
        row.createCell(5).setCellValue(data.get_dob().get(data.get_dob().DAY_OF_MONTH) + "-" + (data.get_dob().get(data.get_dob().MONTH) + 1) + "-" + data.get_dob().get(data.get_dob().YEAR));
        row.createCell(6).setCellValue(data.get_itn().toString());
        row.createCell(7).setCellValue(data.get_postcode());
        row.createCell(8).setCellValue(data.get_country());
        row.createCell(9).setCellValue(data.get_region());
        row.createCell(10).setCellValue(data.get_city());
        row.createCell(11).setCellValue(data.get_street());
        row.createCell(12).setCellValue(data.get_house());
        row.createCell(13).setCellValue(data.get_apartment());
    }
}
