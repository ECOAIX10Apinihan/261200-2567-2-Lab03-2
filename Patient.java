public class Patient {
    private String id;
    private String name;
    private int birthYear;
    private double height;  
    private double weight;
    private String bloodGroup;
    private String phoneNumber;
    int currentYear = 2024;


public Patient(String id, String name, int birthYear, int height, double weight, String bloodGroup, String phoneNumber) {
    this.id = id;
    this.name = name;
    if (birthYear > 0 && birthYear <= 2024) {
        this.birthYear = birthYear;
    }else{
        this.birthYear = Math.abs(birthYear);// Math.abs() ค่าที่เป็นลบเป็นบวก
    }
    this.height = Math.abs(height); // Math.abs() ค่าที่เป็นลบเป็นบวก <- ย่อมาจาก absolute ||
    this.weight = Math.abs(weight); // Math.abs() ค่าที่เป็นลบเป็นบวก
    this.bloodGroup = bloodGroup; 
    this.phoneNumber = phoneNumber;
}


public int getAge(int currentYear) {
    if (birthYear > currentYear) {
        throw new IllegalArgumentException("Current year cannot be less than birth year!");
    }
    return currentYear - birthYear;
}

public double getBMI(double height,double weight){ 
    if (0 < height && height < 300 &&  0 < weight && weight < 400) { // สูงไม่เกิน 300 cm หนักไม่เกิน 400
        double heightInMeters = height / 100.00; // แปลงเซนเป็นเมตร
        return weight / Math.pow(heightInMeters,2.00);// Math.() เลขยกกำลัง แบบง่าย weight / (heightInMeters * heightInMeters)
    }
    return 0.0; // การบ้าน condition return 0.0
}

// ใช้ get method bloodGroup และ phoneNumber การบ้าน
public String getbloodGroup(String bloodGroup){
    if (bloodGroup != "O" && bloodGroup != "A" && bloodGroup != "B" && bloodGroup != "AB") {
        throw new IllegalArgumentException("Patient bloodGroup: Choice O A B AB");
    }
    return bloodGroup;
}

public String getphoneNumber(String phoneNumber){
    if (phoneNumber == "") {
        throw new IllegalArgumentException("Patient phoneNumber Example: 0110110222: Invalid phoneNumber");
    }
    return phoneNumber;
}

public void displayDetails() {
    System.out.println("Patient Name: " + name);
    System.out.println("patient id: " + id);
    
    try {
         System.out.println("Patient Age: " + getAge(currentYear));
    } catch (IllegalArgumentException txtage) {
        System.out.println("Patient Age: " + txtage.getMessage());
    }
   
    System.out.println("Patient Height (cm): " + height);
    System.out.println("Patient Weight (kg): " + weight);

    try {
        System.out.println("Patient bloodGroup: " + getbloodGroup(bloodGroup));
    } catch (IllegalArgumentException txtbloodGroup) {
       System.out.println("Patient Age: " + txtbloodGroup.getMessage());
    }

    if (getBMI(height,weight) == 0.0) { // เพิ่มเติม ถ้าได้ 0.0 จะแสดงใน display 0.0 คำแนะนำ
        System.out.println("Patient BMI (kg/m2):" + getBMI(height,weight) + " Example height 160.0 weight 50.0: Invalid height or weight");

    }else{ // เลขถูก ก็คำนวน ตามปกติ
        System.out.printf("Patient BMI:%.2f%n",getBMI(height,weight)); // คำสั่ง printf หรือ printformat %.2f%n ทศนิยม 2 ตำแหน่ง
    }
    
    try {
        System.out.println("Patient phoneNumber: " + getphoneNumber(phoneNumber));
    } catch (IllegalArgumentException txtphoneNumber) {
       System.out.println("Patient phoneNumber: " + txtphoneNumber.getMessage());
    }

    System.out.println("\n"); // เว้นบรรทัด
}

public static void main(String[] args) {
    
    Patient patient1 = new Patient("1001", "Jack Dawson", 1978, 188, 78.0, "O","0112223333"); // สมบูรณ์แบบ
    patient1.displayDetails();

    Patient patient2 = new Patient("1002", "Rose dewit", 2026, 172, 56.0, "AB","0112223334"); // ปีเกิดอนาคต
    patient2.displayDetails();

    Patient Patient3 = new Patient("1003", "Joe hanna", -1990, -168, -50.0,"A-","0112223335"); // เลขติดลบ กลุ่มเลือด A- ไม่มีในฐานข้อมูล (if condition)
    Patient3.displayDetails();

    Patient Patient4 = new Patient("1004", "Anna era", -2026, -160, -45.0,"A","0112223335"); // เลขติดลบ 
    Patient4.displayDetails();

    Patient Patient5 = new Patient("1005", "Elsa era", 1990, 165, -42.0,"B",""); // ไม่กรอกเบอร์โทร
    Patient5.displayDetails();

    Patient Patient6 = new Patient("test err 1006", "tester", -0, 0, 0,"B-",""); // ผิดหมด
    Patient6.displayDetails();
}
}
