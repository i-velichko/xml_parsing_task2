package com.epam.secondtask.builder.sax;

import com.epam.secondtask.builder.type.MedicineXmlTag;
import com.epam.secondtask.exception.MedicineXmlException;
import com.epam.secondtask.model.Homeopathy;
import com.epam.secondtask.model.Medicine;
import com.epam.secondtask.model.Vaccine;
import com.epam.secondtask.model.Version;
import com.epam.secondtask.model.type.MedicineGroupType;
import com.epam.secondtask.model.type.MedicinePackageType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class MedicineHandler extends DefaultHandler {
    private final static Logger LOGGER = LogManager.getLogger();
    private final List<MedicineXmlTag> anyMedicineTagList = List.of(MedicineXmlTag.MEDICINE, MedicineXmlTag.VACCINE, MedicineXmlTag.HOMEOPATHY);
    private final List<Medicine> medicines;
    private final EnumSet<MedicineXmlTag> medicineXmlTags;
    private Medicine currentMedicine;
    private Version currentVersion;
    private MedicineXmlTag currentXmlTag;
    private List<String> analogs;
    private List<Version> versions;
    private String analog;

    public MedicineHandler() {
        medicines = new ArrayList<>();
        medicineXmlTags = EnumSet.range(MedicineXmlTag.NAME, MedicineXmlTag.BACTERIUM);
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (anyMedicineTagList.contains(MedicineXmlTag.valueOf(qName.toUpperCase()))) {
            try {
                currentMedicine = createSpecificMedicine(qName);
            } catch (MedicineXmlException e) {
                LOGGER.error("Can not build specific medicine instance" + e.getMessage());
            }

            for (int i = 0; i < attrs.getLength(); i++) {
                String attributeName = attrs.getQName(i);

                if (attributeName.equals(MedicineXmlTag.ID.toString())) {
                    String medicineID = attrs.getValue(i);
                    currentMedicine.setMedicineId(medicineID);
                } else if (attributeName.equals(MedicineXmlTag.PRESCRIPTION.toString())) {
                    String prescription = attrs.getValue(i);
                    currentMedicine.setPrescription(prescription != null ? prescription : "true");
                }

            }

        } else {
            MedicineXmlTag temp = MedicineXmlTag.valueOf(qName.toUpperCase());
            if (medicineXmlTags.contains(temp)) {
                currentXmlTag = temp;
            }
            if ("analogs".equals(qName)) {
                analogs = new ArrayList<>();
            }

            if ("versions".equals(qName)) {
                versions = new ArrayList<>();
            }

            if ("version".equals(qName)) {
                currentVersion = new Version();
            }
        }

    }

    private Medicine createSpecificMedicine(String qName) throws MedicineXmlException {
        switch (qName) {
            case "medicine":
                return new Medicine();
            case "homeopathy":
                return new Homeopathy();
            case "vaccine":
                return new Vaccine();
        }
        throw new MedicineXmlException();
    }

    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "medicine", "homeopathy", "vaccine" -> {
                medicines.add(currentMedicine);
                LOGGER.debug("The medicine instance was created and filled with content by SAX parser.");
            }
            case "analogs" -> currentMedicine.setAnalogs(analogs);
            case "analog" -> analogs.add(analog);
            case "versions" -> currentMedicine.setMedicineVersions(versions);
            case "version" -> versions.add(currentVersion);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).strip();
        if (currentXmlTag != null) {
            try {
                switch (currentXmlTag) {
                    case NAME -> currentMedicine.setMedicineName(data);
                    case GROUP -> currentMedicine.setMedicineGroup(MedicineGroupType.valueOf(data.toUpperCase()));
                    case ANALOG -> analog = data;
                    case PHARM -> currentVersion.setPharmCompany(data);
                    case CERTIFICATE -> currentVersion.setMedicineCertificate(data);
                    case PACKAGE -> currentVersion.setMedicinePackage(MedicinePackageType.valueOf(data.toUpperCase()));
                    case DOSAGE -> currentVersion.setMedicineDosage(data);
                    case ACTIVE_SUBSTANCE -> ((Homeopathy) currentMedicine).setActiveSubstance(data);
                    case BACTERIUM -> ((Vaccine) currentMedicine).setBacteria(data);
                    case EXPIRATION_DATE -> currentVersion.setExpirationDate(YearMonth.parse(data));
                }
            } catch (EnumConstantNotPresentException e) {
                LOGGER.error("Error in filling in fields " + e.getMessage());
            }

        }
        currentXmlTag = null;
    }
}

