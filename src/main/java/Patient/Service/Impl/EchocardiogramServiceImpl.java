package Patient.Service.Impl;

import Patient.Dtos.EchocardiogramDto;
import Patient.Exceptions.EchocardiogramNotFoundException;
import Patient.Exceptions.PatientNotFoundException;
import Patient.Models.Echocardiogram;
import Patient.Models.Patient;
import Patient.Repository.EchocardiogramRepository;
import Patient.Repository.PatientRepository;
import Patient.Service.EchocardiogramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EchocardiogramServiceImpl implements EchocardiogramService {

    private EchocardiogramRepository echocardiogramRepository;
    private PatientRepository patientRepository;

    @Autowired
    public EchocardiogramServiceImpl(EchocardiogramRepository echocardiogramRepository, PatientRepository patientRepository) {
        this.echocardiogramRepository = echocardiogramRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public EchocardiogramDto createEchocardiogram(int patientId, EchocardiogramDto echocardiogramDto) {
        Echocardiogram echocardiogram = mapToEntity(echocardiogramDto);
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient with associated echocardiogram not found"));

        echocardiogram.setPatient(patient);
        Echocardiogram newEchocardiogram = echocardiogramRepository.save(echocardiogram);
        return mapToDto(newEchocardiogram);
    }

    @Override
    public List<EchocardiogramDto> getEchocardiogramsByPatientId(int id) {
        List<Echocardiogram> echocardiograms = echocardiogramRepository.findByPatientId(id);

        return echocardiograms.stream().map((e) -> mapToDto(e)).collect(Collectors.toList());
    }

    @Override
    public EchocardiogramDto echocardiogramById(int patientId, int echocardiogramId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found"));

        List<Echocardiogram> echocardiograms = patient.getEchocardiograms();
        Echocardiogram echocardiogram = echocardiograms.stream()
                .filter(e -> e.getId() == echocardiogramId)
                .findFirst()
                .orElseThrow(() -> new EchocardiogramNotFoundException("Echocardiogram not found"));

        return mapToDto(echocardiogram);
    }

    @Override
    public EchocardiogramDto updateEchocardiogram(int patientId, int echocardiogramId, EchocardiogramDto echocardiogramDto) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found"));

        Echocardiogram echocardiogram = echocardiogramRepository.findById(echocardiogramId).orElseThrow(() ->
                new EchocardiogramNotFoundException("Echocardiogram not found"));

        if(echocardiogram.getPatient().getId() != patient.getId()){
            throw new EchocardiogramNotFoundException("This echocardiogram does not belong to a patient");
        }

        echocardiogram.setDate(echocardiogramDto.getDate());
        echocardiogram.setCardiacDimensions(echocardiogramDto.getCardiacDimensions());
        echocardiogram.setEjectionFraction(echocardiogramDto.getEjectionFraction());
        echocardiogram.setValveFunction(echocardiogramDto.getValveFunction());
        echocardiogram.setVelocitiesBloodFlows(echocardiogramDto.getVelocitiesBloodFlows());
        echocardiogram.setMovementCardiacWalls(echocardiogramDto.getMovementCardiacWalls());
        echocardiogram.setPulmonaryArterialPressure(echocardiogramDto.getPulmonaryArterialPressure());
        echocardiogram.setBloodFlow(echocardiogramDto.getBloodFlow());

        Echocardiogram updateEchocardiogram = echocardiogramRepository.save(echocardiogram);
        return mapToDto(updateEchocardiogram);

    }

    @Override
    public void deleteEchocardiogram(int patientId, int echocardiogramId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found"));

        Echocardiogram echocardiogram = echocardiogramRepository.findById(echocardiogramId).orElseThrow(() ->
                new EchocardiogramNotFoundException("Echocardiogram not found"));

        if(echocardiogram.getPatient().getId() != patient.getId()){
            throw new PatientNotFoundException("This echocardiogram does not belong to a patient");
        }

        echocardiogramRepository.delete(echocardiogram);
    }

    private EchocardiogramDto mapToDto(Echocardiogram echocardiogram){
        EchocardiogramDto echocardiogramDto = new EchocardiogramDto();
        echocardiogramDto.setId(echocardiogram.getId());
        echocardiogramDto.setDate(echocardiogram.getDate());
        echocardiogramDto.setCardiacDimensions(echocardiogram.getCardiacDimensions());
        echocardiogramDto.setEjectionFraction(echocardiogram.getEjectionFraction());
        echocardiogramDto.setValveFunction(echocardiogram.getValveFunction());
        echocardiogramDto.setVelocitiesBloodFlows(echocardiogram.getVelocitiesBloodFlows());
        echocardiogramDto.setMovementCardiacWalls(echocardiogram.getMovementCardiacWalls());
        echocardiogramDto.setPulmonaryArterialPressure(echocardiogram.getPulmonaryArterialPressure());
        echocardiogramDto.setBloodFlow(echocardiogram.getBloodFlow());

        return echocardiogramDto;
    }

    private Echocardiogram mapToEntity(EchocardiogramDto echocardiogramDto){
        Echocardiogram echocardiogram = new Echocardiogram();
        echocardiogram.setId(echocardiogramDto.getId());
        echocardiogram.setDate(echocardiogramDto.getDate());
        echocardiogram.setCardiacDimensions(echocardiogramDto.getCardiacDimensions());
        echocardiogram.setEjectionFraction(echocardiogramDto.getEjectionFraction());
        echocardiogram.setValveFunction(echocardiogramDto.getValveFunction());
        echocardiogram.setVelocitiesBloodFlows(echocardiogramDto.getVelocitiesBloodFlows());
        echocardiogram.setMovementCardiacWalls(echocardiogramDto.getMovementCardiacWalls());
        echocardiogram.setPulmonaryArterialPressure(echocardiogramDto.getPulmonaryArterialPressure());
        echocardiogram.setBloodFlow(echocardiogramDto.getBloodFlow());

        return echocardiogram;
    }
}
