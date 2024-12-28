import java.util.Queue;
import java.util.LinkedList;   // LinkedList also implements Queue
import java.util.Set;
import java.util.HashSet;

class Clinic {

    private Queue<Patient> patientQueue;
    private Set<Patient> admittedPatients;
    private int dayCount;

    // Constructor to initialize the clinic with a queue and a set of admitted patients
    public Clinic() {
        this.patientQueue = new LinkedList<>();       // No patients in queue yet
        this.admittedPatients = new HashSet<>();      // No patients admitted yet
        this.dayCount = 1;    // Start with Day 1
    }

    // Admit a patient to the clinic
    public void admitPatient(Patient patient) {
        // Add the patient to the queue and print "Patient <name> admitted."
        patientQueue.offer(patient);
        System.out.println("Patient " + patient.getName() + " admitted.");
    }
    

    // Schedule appointments (maximum 3 patients per day)
    public void getSchedule() {
        if (patientQueue.isEmpty()) {
            System.out.println("No patients in the queue to schedule.");
            return;
        }

        while (!patientQueue.isEmpty()) {
            int patientsScheduledToday = 0;
            System.out.println("Scheduling appointments for Day " + dayCount + ":");
            while (patientsScheduledToday < 3 && !patientQueue.isEmpty()) {
                Patient patient = patientQueue.poll();
                patient.setAppointmentDay("Day " + dayCount);
                admittedPatients.add(patient);
                System.out.println("Scheduled " + patient.getName() + " on " + patient.getAppointmentDay());
                patientsScheduledToday++;
            }
            dayCount++;
            System.out.println(); 
        }
    }


    public void providedTreatment(Patient patient, String treatment) {
        if (admittedPatients.contains(patient)) {
            patient.setTreatment(treatment);
            System.out.println("Treatment provided to " + patient.getName() + ": " + treatment);
        } else {
            System.out.println("Patient not found in the admitted list.");
        }
    }

    public void getPatientDetails(Patient patient) {
        if (admittedPatients.contains(patient)) {
            System.out.println("Patient Details: " + patient);
        } else {
            System.out.println("Patient not found in the admitted list.");
        }
    }


    public void getAppointmentDetails(Patient patient) {
  
        if (admittedPatients.contains(patient)) {
            String appointmentDay = patient.getAppointmentDay();
            if (appointmentDay != null) {
                System.out.println("Appointment Details: " + patient.getName() + " is scheduled on " + appointmentDay);
            } else {
                System.out.println("Appointment Details: " + patient.getName() + " has no scheduled appointment.");
            }
        } else {
            System.out.println("Patient not found in the admitted list.");
        }
    }


    public void dischargePatient(Patient patient) {

        if (admittedPatients.remove(patient)) {
            System.out.println("Patient " + patient.getName() + " discharged.");
        } else {
            System.out.println("Patient not found in the admitted list.");
        }
    }
}
