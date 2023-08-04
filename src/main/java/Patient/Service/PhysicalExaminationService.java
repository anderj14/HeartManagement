package Patient.Service;

import Patient.Dtos.PhysicalExaminationDto;

import java.util.List;

public interface PhysicalExaminationService {
    PhysicalExaminationDto createPhysicalExamination(int patientId, PhysicalExaminationDto physicalExaminationDto);
    List<PhysicalExaminationDto> getPhysicalExamination(int id);
    PhysicalExaminationDto physicalExaminationById(int patientId, int physicalExaminationId);
    PhysicalExaminationDto updatePhysicalExamination(int patientId, int physicalExaminationId, PhysicalExaminationDto physicalExaminationDto);
    void deletePhysicalExamination(int patientId, int physicalExaminationId);
}
