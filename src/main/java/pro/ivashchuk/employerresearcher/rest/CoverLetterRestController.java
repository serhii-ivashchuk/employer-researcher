package pro.ivashchuk.employerresearcher.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.ivashchuk.employerresearcher.domain.CoverLetter;
import pro.ivashchuk.employerresearcher.repository.JpaCoverLetterRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/coverLetters")
public class CoverLetterRestController {

	@Autowired
	private JpaCoverLetterRepository jpaCoverLetterRepository;

	@GetMapping(produces = "application/json")
	public List<CoverLetter> getAllCoverLetters() {
		return jpaCoverLetterRepository.findAll();
	}
}