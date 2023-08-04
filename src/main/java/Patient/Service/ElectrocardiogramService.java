package Patient.Service;

import Patient.Dtos.ElectrocardiogramDto;
import java.util.List;

public interface ElectrocardiogramService {
    ElectrocardiogramDto createElectrocardiogram(int patientId, ElectrocardiogramDto electrocardiogramDto);
    List<ElectrocardiogramDto> getElectrocardiogramsByPatientId(int id);
    ElectrocardiogramDto electrocardiogramById(int patientId, int electrocardiogramId);
    ElectrocardiogramDto updateElectrocardiogram(int patientId, int electrocardiogramId, ElectrocardiogramDto electrocardiogramDto);
    void deleteElectrocardiogram(int patientId, int electrocardiogramId);
}
