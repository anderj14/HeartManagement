package Patient.Service.Impl;

import Patient.Dtos.ElectrocardiogramDto;
import Patient.Exceptions.ElectrocardiogramNotFoundException;
import Patient.Exceptions.PatientNotFoundException;
import Patient.Exceptions.PhysicalExaminationNotFoundException;
import Patient.Models.Electrocardiogram;
import Patient.Models.Patient;
import Patient.Repository.ElectrocardiogramRepository;
import Patient.Repository.PatientRepository;
import Patient.Service.ElectrocardiogramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElectrocardiogramServiceImpl implements ElectrocardiogramService {

    private ElectrocardiogramRepository electrocardiogramRepository;
    private PatientRepository patientRepository;

    @Autowired
    public ElectrocardiogramServiceImpl(ElectrocardiogramRepository electrocardiogramRepository, PatientRepository patientRepository) {
        this.electrocardiogramRepository = electrocardiogramRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public ElectrocardiogramDto createElectrocardiogram(int patientId, ElectrocardiogramDto electrocardiogramDto) {
        Electrocardiogram electrocardiogram = mapToEntity(electrocardiogramDto);
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PhysicalExaminationNotFoundException("Patient with associated electrocardiogram not found"));

        electrocardiogram.setPatient(patient);
        Electrocardiogram newElectrocardiogram = electrocardiogramRepository.save(electrocardiogram);
        return mapToDto(newElectrocardiogram);
    }

    @Override
    public List<ElectrocardiogramDto> getElectrocardiogramsByPatientId(int id) {
        List<Electrocardiogram> electrocardiograms = electrocardiogramRepository.findByPatientId(id);

        return electrocardiograms.stream().map((e) -> mapToDto(e)).collect(Collectors.toList());
    }

    @Override
    public ElectrocardiogramDto electrocardiogramById(int patientId, int electrocardiogramId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PhysicalExaminationNotFoundException("Patient not found"));

        List<Electrocardiogram> electrocardiograms = patient.getElectrocardiograms();

        Electrocardiogram electrocardiogram = electrocardiograms.stream()
                .filter(e -> e.getId() == electrocardiogramId)
                .findFirst()
                .orElseThrow(() -> new ElectrocardiogramNotFoundException("Electrocardiogram not found"));

        return mapToDto(electrocardiogram);
    }

    @Override
    public ElectrocardiogramDto updateElectrocardiogram(int patientId, int electrocardiogramId, ElectrocardiogramDto electrocardiogramDto) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found"));

        Electrocardiogram electrocardiogram = electrocardiogramRepository.findById(electrocardiogramId).orElseThrow(() ->
                new ElectrocardiogramNotFoundException("Electrocardiogram not found"));

        if(electrocardiogram.getPatient().getId() != patient.getId()){
            throw new ElectrocardiogramNotFoundException("This electrocardiogram does not belong to a patient");
        }

        electrocardiogram.setDate(electrocardiogramDto.getDate());
        electrocardiogram.setHeartRhythm(electrocardiogramDto.getHeartRhythm());
        electrocardiogram.setIntervalsSegments(electrocardiogramDto.getIntervalsSegments());
        electrocardiogram.setCharacteristicWaves(electrocardiogramDto.getCharacteristicWaves());
        electrocardiogram.setHeartRate(electrocardiogramDto.getHeartRate());
        electrocardiogram.setAbnormalities(electrocardiogramDto.getAbnormalities());
        electrocardiogram.setArtifacts(electrocardiogramDto.getArtifacts());

        Electrocardiogram updateElectrocardiogram = electrocardiogramRepository.save(electrocardiogram);
        return mapToDto(updateElectrocardiogram);

    }

    @Override
    public void deleteElectrocardiogram(int patientId, int electrocardiogramId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found"));

        Electrocardiogram electrocardiogram = electrocardiogramRepository.findById(electrocardiogramId).orElseThrow(() ->
                new ElectrocardiogramNotFoundException("Electrocardiogram history not found"));

        if(electrocardiogram.getPatient().getId() != patient.getId()){
            throw new ElectrocardiogramNotFoundException("This Electrocardiogram does not belong to a patient");
        }

        electrocardiogramRepository.delete(electrocardiogram);
    }

    private ElectrocardiogramDto mapToDto(Electrocardiogram electrocardiogram){
        ElectrocardiogramDto electrocardiogramDto = new ElectrocardiogramDto();
        electrocardiogramDto.setId(electrocardiogram.getId());
        electrocardiogramDto.setDate(electrocardiogram.getDate());
        electrocardiogramDto.setHeartRhythm(electrocardiogram.getHeartRhythm());
        electrocardiogramDto.setIntervalsSegments(electrocardiogram.getIntervalsSegments());
        electrocardiogramDto.setCharacteristicWaves(electrocardiogram.getCharacteristicWaves());
        electrocardiogramDto.setHeartRate(electrocardiogram.getHeartRate());
        electrocardiogramDto.setAbnormalities(electrocardiogram.getAbnormalities());
        electrocardiogramDto.setArtifacts(electrocardiogram.getArtifacts());

        return electrocardiogramDto;
    }

    private Electrocardiogram mapToEntity(ElectrocardiogramDto electrocardiogramDto){
        Electrocardiogram electrocardiogram = new Electrocardiogram();
        electrocardiogram.setId(electrocardiogramDto.getId());
        electrocardiogram.setDate(electrocardiogramDto.getDate());
        electrocardiogram.setHeartRhythm(electrocardiogramDto.getHeartRhythm());
        electrocardiogram.setIntervalsSegments(electrocardiogramDto.getIntervalsSegments());
        electrocardiogram.setCharacteristicWaves(electrocardiogramDto.getCharacteristicWaves());
        electrocardiogram.setHeartRate(electrocardiogramDto.getHeartRate());
        electrocardiogram.setAbnormalities(electrocardiogramDto.getAbnormalities());
        electrocardiogram.setArtifacts(electrocardiogramDto.getArtifacts());

        return electrocardiogram;
    }
}
