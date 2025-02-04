//package services;

//import models.Medicine;
import java.io.*;
import java.util.*;

public class PharmacyManagement {
    private Medicine[] medicines = new Medicine[10]; 
    private int count = 0; 
    private static final String FILE_NAME = "medicines.txt";

    public PharmacyManagement() {
        loadMedicines();
    }

    public void addMedicine(Medicine med) {
        if (count == medicines.length) {
            expandArray();
        }
        medicines[count++] = med;
        saveMedicines();
        System.out.println("Medicine added successfully: " + med.getName());
    }

    public Medicine getMedicine(int id) {
        for (int i = 0; i < count; i++) {
            if (medicines[i].getId() == id) return medicines[i];
        }
        return null;
    }

    public void updateMedicine(int id, double price, int quantity) {
        Medicine med = getMedicine(id);
        if (med != null) {
            med.setPrice(price);
            med.setQuantity(quantity);
            saveMedicines();
            System.out.println("Medicine updated successfully: " + med.getName());
        } else {
            System.err.println("Error: Medicine with ID " + id + " not found.");
        }
    }

    public void deleteMedicine(int id) {
        for (int i = 0; i < count; i++) {
            if (medicines[i].getId() == id) {
                for (int j = i; j < count - 1; j++) {
                    medicines[j] = medicines[j + 1]; 
                }
                medicines[--count] = null;
                saveMedicines();
                System.out.println("Medicine deleted successfully.");
                return;
            }
        }
        System.err.println("Error: Medicine with ID " + id + " not found.");
    }

    private void expandArray() {
        Medicine[] newMedicines = new Medicine[medicines.length * 2]; 
        System.arraycopy(medicines, 0, newMedicines, 0, medicines.length);
        medicines = newMedicines;
    }

    private void saveMedicines() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (int i = 0; i < count; i++) {
                Medicine med = medicines[i];
                writer.write(med.getId() + "," + med.getName() + "," + med.getPrice() + "," + med.getQuantity());
                writer.newLine();
            }
            System.out.println("Medicines saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving medicines: " + e.getMessage());
        }
    }

    private void loadMedicines() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No previous medicines found. Starting fresh.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    try {
                        int id = Integer.parseInt(data[0]);
                        String name = data[1];
                        double price = Double.parseDouble(data[2]);
                        int quantity = Integer.parseInt(data[3]);

                        addMedicine(new Medicine(id, name, price, quantity));
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping invalid medicine entry: " + line);
                    }
                }
            }
            System.out.println("Medicines loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading medicines: " + e.getMessage());
        }
    }
}
