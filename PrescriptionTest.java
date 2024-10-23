import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.text.SimpleDateFormat;
import static org.junit.Assert.*;
import java.text.ParseException;

public class PrescriptionTest {

    private Prescription prescription;
    private Date date;

    @Before
    public void setUp() throws ParseException {
        prescription = new Prescription();
        String format = "dd-MM-yyyy";
        SimpleDateFormat formater = new SimpleDateFormat(format);
        date = formater.parse(formater.format(new Date()));
    }

    @Test
    public void testFirstNameLength() {
        prescription.setPrescription(1, "Jo", "Libert", "12 Pink Lane, Melbourne, 3000, Australia", 0, 0, 0, date, "Dr. Smith");
        assertFalse(prescription.addPrescription()); // Invalid first name
    }

    @Test
    public void testLastNameLength() {
        prescription.setPrescription(1, "Johan", "Li", "12 Pink Lane, Melbourne, 3000, Australia", 0, 0, 0, date, "Dr. Smith");
        assertFalse(prescription.addPrescription()); // Invalid last name
    }

    @Test
    public void testAddressLength() {
        prescription.setPrescription(1, "Johan", "Libert", "12 Pink Lane, Melbourne, 3000, Australia", 0, 0, 0, date, "Dr. Smith");
        assertFalse(prescription.addPrescription()); // Invalid address length
    }

    @Test
    public void testSphere() {
        prescription.setPrescription(1, "Johan", "Libert", "12 Pink Lane, Melbourne, 3000, Australia", -24, 0, 0, date, "Dr. Smith");
        assertFalse(prescription.addPrescription()); // Invalid sphere value
    }

    @Test
    public void testOptometrist() {
        prescription.setPrescription(1, "Johan", "Libert", "12 Pink Lane, Melbourne, 3000, Australia", 0, 0, -5, date, "Bunta");
        assertFalse(prescription.addPrescription()); // Invalid Optometrist
    }

    @Test
    public void testAxis() {
        prescription.setPrescription(1, "Johan", "Libert", "12 Pink Lane, Melbourne, 3000, Australia", 0, 190, 0, date, "Dr. Smith");
        assertFalse(prescription.addPrescription()); // Invalid axis value
    }

    @Test
    
    public void testPostRemarksCount() {
        prescription.setRemark("A short but still valid remark", "Client");
        prescription.setRemark("A short but still valid remark", "Client");
        prescription.setRemark("A short but still valid remark", "Client");
        assertFalse(prescription.addRemark()); // Exceeds remark limit
    }

    @Test
    public void testRemarkType() {
        prescription.setRemark("A short but still valid remark", "Doctor");
        assertFalse(prescription.addRemark()); // Invalid remark type
    }
    @Test
    public void testRemarkType2() {
        prescription.setRemark("A short but still valid remark", "Optometrist");
        assertTrue(prescription.addRemark()); // Invalid remark type
    }

    @Test
    public void testRemarkWordCountMin() {
        prescription.setRemark("Short remark.", "Client");
        assertFalse(prescription.addRemark()); // Invalid word count
    }

    @Test
    public void testRemarkStartsWithUpperCase() {
        prescription.setRemark("no capital letter in this remark", "Client");
        assertFalse(prescription.addRemark()); // Invalid first character
    }

    @Test
    public void testRemarkWordCountMax() {
        prescription.setRemark("A B R O K E N U P R E M A R K A B C D E  F G H I J K", "Optometrist");
        assertFalse(prescription.addRemark()); // Invalid word count
    }
    
}