import java.util.Calendar;


public class Data {
    private String name;
    private String surname;
    private String patronymic;
    private int age;
    private String sex;
    private Calendar dob;
    private Long itn;
    private int postcode;
    private String country;
    private String region;
    private String city;
    private String street;
    private int house;
    private int apartment;

    public Data() {
    }

    public Data(String name, String surname, String patronymic, int age, String sex, Calendar dob, Long itn, int postcode, String country, String region, String city, String street, int house, int apartment) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.age = age;
        this.sex = sex;
        this.dob = dob;
        this.itn = itn;
        this.postcode = postcode;
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
    }

    public String get_name() {
        return name;
    }

    public void set_name(String name) {
        this.name = name;
    }

    public String get_surname() {
        return surname;
    }

    public void set_surname(String surname) {
        this.surname = surname;
    }

    public String get_patronymic() {
        return patronymic;
    }

    public void set_patronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int get_age() {
        return age;
    }

    public void set_age(int age) {
        this.age = age;
    }

    public String get_sex() {
        return sex;
    }

    public void set_sex(String sex) {
        this.sex = sex;
    }

    public Calendar get_dob() {
        return dob;
    }

    public void set_dob(Calendar dob) {
        this.dob = dob;
    }

    public Long get_itn() {
        return itn;
    }

    public void set_itn(Long itn) {
        this.itn = itn;
    }

    public int get_postcode() {
        return postcode;
    }

    public void set_postcode(int postcode) {
        this.postcode = postcode;
    }

    public String get_country() {
        return country;
    }

    public void set_country(String country) {
        this.country = country;
    }

    public String get_region() {
        return region;
    }

    public void set_region(String region) {
        this.region = region;
    }

    public String get_city() {
        return city;
    }

    public void set_city(String city) {
        this.city = city;
    }

    public String get_street() {
        return street;
    }

    public void set_street(String street) {
        this.street = street;
    }

    public int get_house() {
        return house;
    }

    public void set_house(int house) {
        this.house = house;
    }

    public int get_apartment() {
        return apartment;
    }

    public void set_apartment(int apartment) {
        this.apartment = apartment;
    }
}
