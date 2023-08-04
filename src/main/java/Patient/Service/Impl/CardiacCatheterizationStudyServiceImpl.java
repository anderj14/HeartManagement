package Patient.Service.Impl;

import Patient.Dtos.CardiacCatheterizationStudyDto;
import Patient.Exceptions.CardiacCatheterizationStudyNotFoundException;
import Patient.Exceptions.PatientNotFoundException;
import Patient.Models.CardiacCatheterizationStudy;
import Patient.Models.Patient;
import Patient.Repository.CardiacCatheterizationStudyRepository;
import Patient.Repository.PatientRepository;
import Patient.Service.CardiacCatheterizationStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardiacCatheterizationStudyServiceImpl implements CardiacCatheterizationStudyService {
    private CardiacCatheterizationStudyRepository cardiacCathStRepository;
    private PatientRepository patientRepository;

    @Autowired
    public CardiacCatheterizationStudyServiceImpl(CardiacCatheterizationStudyRepository cardiacCathStRepository, PatientRepository patientRepository) {
        this.cardiacCathStRepository = cardiacCathStRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public CardiacCatheterizationStudyDto createCardiacCatheterizationStudy(int patientId, CardiacCatheterizationStudyDto cardiacCatheterizationStudyDto) {
        CardiacCatheterizationStudy cardiacCatheterizationStudy = mapToEntity(cardiacCatheterizationStudyDto);
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient with associated cardiac catheterization study not found"));

        cardiacCatheterizationStudy.setPatient(patient);
        CardiacCatheterizationStudy newCardiacCatheterizationStudy = cardiacCathStRepository.save(cardiacCatheterizationStudy);
        return mapToDto(newCardiacCatheterizationStudy);
    }

    @Override
    public List<CardiacCatheterizationStudyDto> getCardiacCatheterizationStudiesByPatientId(int id) {
        List<CardiacCatheterizationStudy> cardiacCatheterizationStudies = cardiacCathStRepository.findByPatientId(id);

        return cardiacCatheterizationStudies.stream().map((e) -> mapToDto(e)).collect(Collectors.toList());
    }

    @Override
    public CardiacCatheterizationStudyDto CardiacCatheterizationStudyById(int patientId, int cardiacCatheterizationStudyId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found"));

        List<CardiacCatheterizationStudy> cardiacCatheterizationStudies = patient.getCardiacCatheterizationStudies();
        CardiacCatheterizationStudy cardiacCatheterizationStudy = cardiacCatheterizationStudies.stream()
                .filter(ccs -> ccs.getId() == cardiacCatheterizationStudyId)
                .findFirst()
                .orElseThrow(() -> new CardiacCatheterizationStudyNotFoundException("Cardiac catheterization study not found"));

        return mapToDto(cardiacCatheterizationStudy);
    }

    @Override
    public CardiacCatheterizationStudyDto updateCardiacCatheterizationStudyById(int patientId, int cardiacCatheterizationStudyId, CardiacCatheterizationStudyDto cardiacCatheterizationStudyDto) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found"));
        CardiacCatheterizationStudy cardiacCatheterizationStudy = cardiacCathStRepository.findById(cardiacCatheterizationStudyId).orElseThrow(() ->
                new CardiacCatheterizationStudyNotFoundException("Cardiac catheterization study not found"));

        if(cardiacCatheterizationStudy.getPatient().getId() != patient.getId()){
            throw new CardiacCatheterizationStudyNotFoundException("This cardiac catheterization study does not belong to a patient");
        }

        cardiacCatheterizationStudy.setDate(cardiacCatheterizationStudyDto.getDate());
        cardiacCatheterizationStudy.setTime(cardiacCatheterizationStudyDto.getTime());
        cardiacCatheterizationStudy.setNumLocationMainCoronary(cardiacCatheterizationStudyDto.getNumLocationMainCoronary());
        cardiacCatheterizationStudy.setBlockageEachCoronaryArtery(cardiacCatheterizationStudyDto.getBlockageEachCoronaryArtery());
        cardiacCatheterizationStudy.setDescriptionAbnormality(cardiacCatheterizationStudyDto.getDescriptionAbnormality());
        cardiacCatheterizationStudy.setBloodPressureAorta(cardiacCatheterizationStudyDto.getBloodPressureAorta());
        cardiacCatheterizationStudy.setChambersLeftAtrium(cardiacCatheterizationStudyDto.getChambersLeftAtrium());
        cardiacCatheterizationStudy.setChambersLeftVentricle(cardiacCatheterizationStudyDto.getChambersLeftVentricle());
        cardiacCatheterizationStudy.setChambersRightAtrium(cardiacCatheterizationStudyDto.getChambersRightAtrium());
        cardiacCatheterizationStudy.setChambersRightVentricle(cardiacCatheterizationStudyDto.getChambersRightVentricle());
        cardiacCatheterizationStudy.setBloodFlowCoronaryArteries(cardiacCatheterizationStudyDto.getBloodFlowCoronaryArteries());
        cardiacCatheterizationStudy.setVelocityBloodFlow(cardiacCatheterizationStudyDto.getVelocityBloodFlow());
        cardiacCatheterizationStudy.setLeftVentricularEjectionFraction(cardiacCatheterizationStudyDto.getLeftVentricularEjectionFraction());
        cardiacCatheterizationStudy.setBloodPressurePulmonaryArteries(cardiacCatheterizationStudyDto.getBloodPressurePulmonaryArteries());
        cardiacCatheterizationStudy.setValvularInsufficiencyAortic(cardiacCatheterizationStudyDto.getValvularInsufficiencyAortic());
        cardiacCatheterizationStudy.setValvularInsufficiencyMitral(cardiacCatheterizationStudyDto.getValvularInsufficiencyMitral());
        cardiacCatheterizationStudy.setValvularInsufficiencyPulmonary(cardiacCatheterizationStudyDto.getValvularInsufficiencyPulmonary());
        cardiacCatheterizationStudy.setValvularInsufficiencyTricuspid(cardiacCatheterizationStudyDto.getValvularInsufficiencyTricuspid());
        cardiacCatheterizationStudy.setPressureGradientValves(cardiacCatheterizationStudyDto.getPressureGradientValves());
        cardiacCatheterizationStudy.setStructuralAbnormalities(cardiacCatheterizationStudyDto.getStructuralAbnormalities());
        cardiacCatheterizationStudy.setFunctionsCardiacChambers(cardiacCatheterizationStudyDto.getFunctionsCardiacChambers());
        cardiacCatheterizationStudy.setDescriptionComplication(cardiacCatheterizationStudyDto.getDescriptionComplication());
        cardiacCatheterizationStudy.setConclusion(cardiacCatheterizationStudyDto.getConclusion());

        CardiacCatheterizationStudy updateCardiacCatheterizationStudy = cardiacCathStRepository.save(cardiacCatheterizationStudy);
        return mapToDto(updateCardiacCatheterizationStudy);
    }

    @Override
    public void deleteCardiacCatheterizationStudyById(int patientId, int cardiacCatheterizationStudyId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new PatientNotFoundException("Patient not found"));
        CardiacCatheterizationStudy cardiacCatheterizationStudy = cardiacCathStRepository.findById(cardiacCatheterizationStudyId).orElseThrow(() ->
                new CardiacCatheterizationStudyNotFoundException("Cardiac catheterization study not found"));

        if(cardiacCatheterizationStudy.getPatient().getId() != patient.getId()){
            throw new CardiacCatheterizationStudyNotFoundException("This cardiac catheterization study does not belong to a patient");
        }

        cardiacCathStRepository.delete(cardiacCatheterizationStudy);
    }

    private CardiacCatheterizationStudyDto mapToDto(CardiacCatheterizationStudy cardiacCatheterizationStudy){
        CardiacCatheterizationStudyDto cardiacCatheterizationStudyDto = new CardiacCatheterizationStudyDto();

        cardiacCatheterizationStudyDto.setId(cardiacCatheterizationStudy.getId());
        cardiacCatheterizationStudyDto.setDate(cardiacCatheterizationStudy.getDate());
        cardiacCatheterizationStudyDto.setTime(cardiacCatheterizationStudy.getTime());
        cardiacCatheterizationStudyDto.setNumLocationMainCoronary(cardiacCatheterizationStudy.getNumLocationMainCoronary());
        cardiacCatheterizationStudyDto.setBlockageEachCoronaryArtery(cardiacCatheterizationStudy.getBlockageEachCoronaryArtery());
        cardiacCatheterizationStudyDto.setDescriptionAbnormality(cardiacCatheterizationStudy.getDescriptionAbnormality());
        cardiacCatheterizationStudyDto.setBloodPressureAorta(cardiacCatheterizationStudy.getBloodPressureAorta());
        cardiacCatheterizationStudyDto.setChambersLeftAtrium(cardiacCatheterizationStudy.getChambersLeftAtrium());
        cardiacCatheterizationStudyDto.setChambersLeftVentricle(cardiacCatheterizationStudy.getChambersLeftVentricle());
        cardiacCatheterizationStudyDto.setChambersRightAtrium(cardiacCatheterizationStudy.getChambersRightAtrium());
        cardiacCatheterizationStudyDto.setChambersRightVentricle(cardiacCatheterizationStudy.getChambersRightVentricle());
        cardiacCatheterizationStudyDto.setBloodFlowCoronaryArteries(cardiacCatheterizationStudy.getBloodFlowCoronaryArteries());
        cardiacCatheterizationStudyDto.setVelocityBloodFlow(cardiacCatheterizationStudy.getVelocityBloodFlow());
        cardiacCatheterizationStudyDto.setLeftVentricularEjectionFraction(cardiacCatheterizationStudy.getLeftVentricularEjectionFraction());
        cardiacCatheterizationStudyDto.setBloodPressurePulmonaryArteries(cardiacCatheterizationStudy.getBloodPressurePulmonaryArteries());
        cardiacCatheterizationStudyDto.setValvularInsufficiencyAortic(cardiacCatheterizationStudy.getValvularInsufficiencyAortic());
        cardiacCatheterizationStudyDto.setValvularInsufficiencyMitral(cardiacCatheterizationStudy.getValvularInsufficiencyMitral());
        cardiacCatheterizationStudyDto.setValvularInsufficiencyPulmonary(cardiacCatheterizationStudy.getValvularInsufficiencyPulmonary());
        cardiacCatheterizationStudyDto.setValvularInsufficiencyTricuspid(cardiacCatheterizationStudy.getValvularInsufficiencyTricuspid());
        cardiacCatheterizationStudyDto.setPressureGradientValves(cardiacCatheterizationStudy.getPressureGradientValves());
        cardiacCatheterizationStudyDto.setStructuralAbnormalities(cardiacCatheterizationStudy.getStructuralAbnormalities());
        cardiacCatheterizationStudyDto.setFunctionsCardiacChambers(cardiacCatheterizationStudy.getFunctionsCardiacChambers());
        cardiacCatheterizationStudyDto.setDescriptionComplication(cardiacCatheterizationStudy.getDescriptionComplication());
        cardiacCatheterizationStudyDto.setConclusion(cardiacCatheterizationStudy.getConclusion());

        return cardiacCatheterizationStudyDto;
    }

    private CardiacCatheterizationStudy mapToEntity(CardiacCatheterizationStudyDto cardiacCatheterizationStudyDto){
        CardiacCatheterizationStudy cardiacCatheterizationStudy = new CardiacCatheterizationStudy();

        cardiacCatheterizationStudy.setId(cardiacCatheterizationStudyDto.getId());
        cardiacCatheterizationStudy.setDate(cardiacCatheterizationStudyDto.getDate());
        cardiacCatheterizationStudy.setTime(cardiacCatheterizationStudyDto.getTime());
        cardiacCatheterizationStudy.setNumLocationMainCoronary(cardiacCatheterizationStudyDto.getNumLocationMainCoronary());
        cardiacCatheterizationStudy.setBlockageEachCoronaryArtery(cardiacCatheterizationStudyDto.getBlockageEachCoronaryArtery());
        cardiacCatheterizationStudy.setDescriptionAbnormality(cardiacCatheterizationStudyDto.getDescriptionAbnormality());
        cardiacCatheterizationStudy.setBloodPressureAorta(cardiacCatheterizationStudyDto.getBloodPressureAorta());
        cardiacCatheterizationStudy.setChambersLeftAtrium(cardiacCatheterizationStudyDto.getChambersLeftAtrium());
        cardiacCatheterizationStudy.setChambersLeftVentricle(cardiacCatheterizationStudyDto.getChambersLeftVentricle());
        cardiacCatheterizationStudy.setChambersRightAtrium(cardiacCatheterizationStudyDto.getChambersRightAtrium());
        cardiacCatheterizationStudy.setChambersRightVentricle(cardiacCatheterizationStudyDto.getChambersRightVentricle());
        cardiacCatheterizationStudy.setBloodFlowCoronaryArteries(cardiacCatheterizationStudyDto.getBloodFlowCoronaryArteries());
        cardiacCatheterizationStudy.setVelocityBloodFlow(cardiacCatheterizationStudyDto.getVelocityBloodFlow());
        cardiacCatheterizationStudy.setLeftVentricularEjectionFraction(cardiacCatheterizationStudyDto.getLeftVentricularEjectionFraction());
        cardiacCatheterizationStudy.setBloodPressurePulmonaryArteries(cardiacCatheterizationStudyDto.getBloodPressurePulmonaryArteries());
        cardiacCatheterizationStudy.setValvularInsufficiencyAortic(cardiacCatheterizationStudyDto.getValvularInsufficiencyAortic());
        cardiacCatheterizationStudy.setValvularInsufficiencyMitral(cardiacCatheterizationStudyDto.getValvularInsufficiencyMitral());
        cardiacCatheterizationStudy.setValvularInsufficiencyPulmonary(cardiacCatheterizationStudyDto.getValvularInsufficiencyPulmonary());
        cardiacCatheterizationStudy.setValvularInsufficiencyTricuspid(cardiacCatheterizationStudyDto.getValvularInsufficiencyTricuspid());
        cardiacCatheterizationStudy.setPressureGradientValves(cardiacCatheterizationStudyDto.getPressureGradientValves());
        cardiacCatheterizationStudy.setStructuralAbnormalities(cardiacCatheterizationStudyDto.getStructuralAbnormalities());
        cardiacCatheterizationStudy.setFunctionsCardiacChambers(cardiacCatheterizationStudyDto.getFunctionsCardiacChambers());
        cardiacCatheterizationStudy.setDescriptionComplication(cardiacCatheterizationStudyDto.getDescriptionComplication());
        cardiacCatheterizationStudy.setConclusion(cardiacCatheterizationStudyDto.getConclusion());

        return cardiacCatheterizationStudy;
    }
}
