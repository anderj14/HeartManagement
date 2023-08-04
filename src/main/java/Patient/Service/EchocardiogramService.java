package Patient.Service;

import Patient.Dtos.EchocardiogramDto;

import java.util.List;

public interface EchocardiogramService {
    EchocardiogramDto createEchocardiogram(int patientId, EchocardiogramDto echocardiogramDto);
    List<EchocardiogramDto> getEchocardiogramsByPatientId(int id);
    EchocardiogramDto echocardiogramById(int patientId, int echocardiogramId);
    EchocardiogramDto updateEchocardiogram(int patientId, int echocardiogramId, EchocardiogramDto echocardiogramDto);
    void deleteEchocardiogram(int patientId, int echocardiogramId);
}
