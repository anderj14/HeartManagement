package Patient.Service.Impl;

import Patient.Dtos.HolterStudyDto;
import Patient.Exceptions.HolterStudyNotFoundException;
import Patient.Exceptions.PatientNotFoundException;
import Patient.Models.HolterStudy;
import Patient.Models.Patient;
import Patient.Repository.HolterStudyRepository;
import Patient.Repository.PatientRepository;
import Patient.Service.HolterStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HolterStudyServiceImpl implements HolterStudyService {

    private HolterStudyRepository holterStudyRepository;
    private PatientRepository patientRepository;

    @Autowired
    public HolterStudyServiceImpl(HolterStudyRepository holterStudyRepository, PatientRepository patientRepository) {
        this.holterStudyRepository = holterStudyRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public HolterStudyDto createHolterStudy(int patientId, HolterStudyDto holterStudyDto) {
        HolterStudy holterStudy = mapToEntity(holterStudyDto);
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient with associated holter study not found"));

        holterStudy.setPatient(patient);
        HolterStudy updateHolterStudy = holterStudyRepository.save(holterStudy);
        return mapToDto(updateHolterStudy);
    }

    @Override
    public List<HolterStudyDto> getHolterStudiesByPatientId(int id) {
        List<HolterStudy> holterStudies =holterStudyRepository.findByPatientId(id);

        return  holterStudies.stream().map((hs) -> mapToDto(hs)).collect(Collectors.toList());
    }

    @Override
    public HolterStudyDto holterStudyById(int patientId, int holterStudyId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found"));

        List<HolterStudy> holterStudies = patient.getHolterStudies();
        HolterStudy holterStudy = holterStudies.stream()
                .filter(hs -> hs.getId() == holterStudyId)
                .findFirst()
                .orElseThrow(() -> new HolterStudyNotFoundException("Holter study not found"));

        return mapToDto(holterStudy);
    }

    @Override
    public HolterStudyDto updateHolterStudy(int patientId, int holterStudyId, HolterStudyDto holterStudyDto) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found"));

        HolterStudy holterStudy = holterStudyRepository.findById(holterStudyId).orElseThrow(() ->
                new HolterStudyNotFoundException("Holter study not found"));

        if(holterStudy.getPatient().getId() != patient.getId()){
            throw  new HolterStudyNotFoundException("This holter study does not belong to a holter study");
        }

        holterStudy.setDate(holterStudyDto.getDate());
        holterStudy.setTime(holterStudyDto.getTime());
        holterStudy.setStudyDuration(holterStudyDto.getStudyDuration());
        holterStudy.setAverageHeartRate(holterStudyDto.getAverageHeartRate());
        holterStudy.setMaximumHeartRate(holterStudyDto.getMaximumHeartRate());
        holterStudy.setTypeHeartRhythm(holterStudyDto.getTypeHeartRhythm());
        holterStudy.setArrhythmiaEpisodes(holterStudyDto.getArrhythmiaEpisodes());
        holterStudy.setPhysicalActivity(holterStudyDto.getPhysicalActivity());
        holterStudy.setPatientSymptoms(holterStudyDto.getPatientSymptoms());
        holterStudy.setConclusion(holterStudyDto.getConclusion());

        HolterStudy updateHolterStudy = holterStudyRepository.save(holterStudy);
        return mapToDto(updateHolterStudy);
    }

    @Override
    public void deleteHolterStudy(int patientId, int holterStudyId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found"));

        HolterStudy holterStudy = holterStudyRepository.findById(holterStudyId).orElseThrow(() ->
                new HolterStudyNotFoundException("Holter study not found"));

        if(holterStudy.getPatient().getId() != patient.getId()){
            throw  new HolterStudyNotFoundException("This holter study does not belong to a holter study");
        }

        holterStudyRepository.delete(holterStudy);
    }

    private HolterStudyDto mapToDto(HolterStudy holterStudy){
        HolterStudyDto holterStudyDto = new HolterStudyDto();
        holterStudyDto.setId(holterStudy.getId());
        holterStudyDto.setDate(holterStudy.getDate());
        holterStudyDto.setTime(holterStudy.getTime());
        holterStudyDto.setStudyDuration(holterStudy.getStudyDuration());
        holterStudyDto.setAverageHeartRate(holterStudy.getAverageHeartRate());
        holterStudyDto.setMaximumHeartRate(holterStudy.getMaximumHeartRate());
        holterStudyDto.setTypeHeartRhythm(holterStudy.getTypeHeartRhythm());
        holterStudyDto.setArrhythmiaEpisodes(holterStudy.getArrhythmiaEpisodes());
        holterStudyDto.setPhysicalActivity(holterStudy.getPhysicalActivity());
        holterStudyDto.setPatientSymptoms(holterStudy.getPatientSymptoms());
        holterStudyDto.setConclusion(holterStudy.getConclusion());

        return holterStudyDto;
    }

    private HolterStudy mapToEntity(HolterStudyDto holterStudyDto){
        HolterStudy holterStudy = new HolterStudy();
        holterStudy.setId(holterStudyDto.getId());
        holterStudy.setDate(holterStudyDto.getDate());
        holterStudy.setTime(holterStudyDto.getTime());
        holterStudy.setStudyDuration(holterStudyDto.getStudyDuration());
        holterStudy.setAverageHeartRate(holterStudyDto.getAverageHeartRate());
        holterStudy.setMaximumHeartRate(holterStudyDto.getMaximumHeartRate());
        holterStudy.setTypeHeartRhythm(holterStudyDto.getTypeHeartRhythm());
        holterStudy.setArrhythmiaEpisodes(holterStudyDto.getArrhythmiaEpisodes());
        holterStudy.setPhysicalActivity(holterStudyDto.getPhysicalActivity());
        holterStudy.setPatientSymptoms(holterStudyDto.getPatientSymptoms());
        holterStudy.setConclusion(holterStudyDto.getConclusion());

        return holterStudy;
    }
}
