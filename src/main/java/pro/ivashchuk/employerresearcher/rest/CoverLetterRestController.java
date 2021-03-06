package pro.ivashchuk.employerresearcher.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.ivashchuk.employerresearcher.domain.CoverLetter;
import pro.ivashchuk.employerresearcher.domain.Vacancy;
import pro.ivashchuk.employerresearcher.repository.JpaCoverLetterRepository;
import pro.ivashchuk.employerresearcher.repository.JpaVacancyRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/coverLetters")
public class CoverLetterRestController {

	@Autowired
	private JpaCoverLetterRepository jpaCoverLetterRepository;

	@Autowired
	private JpaVacancyRepository jpaVacancyRepository;

	@GetMapping(produces = "application/json")
	public List<CoverLetter> getAllCoverLetters() {
		return jpaCoverLetterRepository.findAll();
	}

	@GetMapping(path = "/coverLetter/{id}", produces = "application/json")
	public CoverLetter getCoverLetterById(@PathVariable("id") Long id) {
		return jpaCoverLetterRepository.findById(id).get();
	}

	@PostMapping("/coverLetter/addNewCoverLetter/whereVacancyId/{id}")
	public HttpStatus postNewCoverLetterWhereVacancyId(@PathVariable("id") Long id, @RequestBody CoverLetter coverLetter) {
		Vacancy vacancy = jpaVacancyRepository.findById(id).get();
		jpaCoverLetterRepository.save(coverLetter);
		vacancy.getCoverLetters().add(coverLetter);
		jpaVacancyRepository.save(vacancy);
		coverLetter.setVacancy(vacancy);
		jpaCoverLetterRepository.save(coverLetter);
		return HttpStatus.CREATED;
	}

	@PutMapping("/coverLetter/{id}/update")
	public ResponseEntity<CoverLetter> putCoverLetter(@PathVariable("id") Long id,
	                                           CoverLetter updatedCoverLetter) {
		Optional<CoverLetter> coverLetterData = jpaCoverLetterRepository.findById(id);
		if (coverLetterData.isPresent()) {
			CoverLetter coverLetterToUpdate = coverLetterData.get();
			return new ResponseEntity<>(jpaCoverLetterRepository.save(coverLetterToUpdate), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("employer/{id}/delete")
	public String deleteCoverLetter(@PathVariable("id") Long id) {
		jpaCoverLetterRepository.delete(jpaCoverLetterRepository.findById(id).get());
		return "redirect:localhost:4200/coverLetterList";
	}
}