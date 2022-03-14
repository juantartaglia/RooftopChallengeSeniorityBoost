package com.rooftop.challenge.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.rooftop.challenge.model.Text;
import com.rooftop.challenge.model.dto.RequestDTO;
import com.rooftop.challenge.model.dto.ResponseDTO;
import com.rooftop.challenge.model.dto.ResponseTextDTO;
import com.rooftop.challenge.repository.TextRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rooftop.challenge.controller.utils.TextUtils;
import com.rooftop.challenge.errorhandler.CustomException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class TextController {
    
    private final String BASE_URL = "/text";

    @Autowired private TextRepository textRepository;

    Logger logger = LoggerFactory.getLogger(TextController.class);

    @PostMapping(BASE_URL)
    public ResponseEntity<ResponseDTO> save(@RequestBody RequestDTO requestDTO) {
        try {
            String textStr = requestDTO.getText();
            Integer chars = requestDTO.getChars();

            ResponseDTO response = new ResponseDTO();
            if (chars == null) chars = 2;
            if (chars > textStr.length()) chars = textStr.length();

            String queryHash = TextUtils.calculateHash(String.format("%s.%s",TextUtils.calculateHash(textStr),TextUtils.calculateHash(chars.toString())));
            Optional<Text> result = textRepository.findByHashAndDeleted(queryHash, false);
            Text text = new Text();
            if (result.isPresent()) {
                text = result.get();
                
                response.setId(text.getId());
                response.setUrl(String.format("%s/%s", BASE_URL, text.getId().toString()));
            } else {
                text.setChars(chars);
                text.setHash(queryHash);
                text.setResult(TextUtils.calculateResult(textStr, chars));
                text.setDeleted(false);
                
                Long id = textRepository.save(text).getId();
                
                response.setId(id);
                response.setUrl(String.format("%s/%s", BASE_URL, id.toString()));
            }
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }
        catch(Exception e) {
            logger.error("Error: save() ", e.getMessage());
            throw new CustomException("An error occurred when processing the text", true, HttpStatus.UNPROCESSABLE_ENTITY.value());
        }        
    }


    @GetMapping(BASE_URL+ "/{id}")
    public ResponseEntity<ResponseTextDTO> getTextById(@PathVariable Long id){
        try {
            Optional<Text> response= textRepository.findByIdAndDeleted(id, false);
            if (response.isPresent()) {
                Text text = response.get();
                return new ResponseEntity<>(new ResponseTextDTO(text.getId(), text.getHash(), text.getChars(), text.getResult()), HttpStatus.OK);
            }
        }
        catch (Exception e) {
            logger.error("Error: getTextById() ", e.getMessage());
            throw new CustomException("An error occurred when processing the text", true, HttpStatus.UNPROCESSABLE_ENTITY.value());
        }
        throw new CustomException("Text not found", true, HttpStatus.NOT_FOUND.value());
    }


    @GetMapping(BASE_URL)
    public ResponseEntity<List<ResponseTextDTO>> getTextPaginated(@RequestParam(required = false, defaultValue = "2") String chars, @RequestParam(required = false, defaultValue="1") String page, @RequestParam(required = false) String rpp) {
        List<ResponseTextDTO> textList = new ArrayList<ResponseTextDTO>();
        try {
            if (rpp == null) {
                textRepository.findAllByCharsAndDeleted(Integer.valueOf(chars), false).forEach(e -> {
                    textList.add(new ResponseTextDTO(e.getId(), e.getHash(), e.getChars(), e.getResult()));
                });
            }
            else {
                Integer rppInt = Integer.valueOf(rpp);
                if (rppInt < 10 || rppInt > 100) rppInt = (rppInt < 10 ? 10 : 100);

                Pageable paging = PageRequest.of(Integer.valueOf(page), rppInt);
                textRepository.findAllByCharsAndDeleted(Integer.valueOf(chars), false, paging).forEach(e -> {
                    textList.add(new ResponseTextDTO(e.getId(), e.getHash(), e.getChars(), e.getResult()));
                });
            }
        }
        catch (Exception e) {
            logger.error("Error: getTextPaginated() ", e.getMessage());
            throw new CustomException("An error occurred when processing the text", true, HttpStatus.UNPROCESSABLE_ENTITY.value());
        }

        if (textList.isEmpty()) {
            throw new CustomException("Text not found", true, HttpStatus.NOT_FOUND.value());
        }
        return new ResponseEntity<>(textList, HttpStatus.OK);
    }


    @DeleteMapping(BASE_URL+"/{id}")
    public ResponseEntity<ResponseDTO> deleteTextByID(@PathVariable Long id) {
        try {
            Optional<Text> response= textRepository.findByIdAndDeleted(id, false);
            if (response.isPresent()) {
                Text text = response.get();
                text.setDeleted(true);
                textRepository.save(text);
                return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
            }
        }
        catch(Exception e) {
            logger.error("Error: deleteTextById() ", e.getMessage());
            throw new CustomException("An error occurred when processing the text", true, HttpStatus.UNPROCESSABLE_ENTITY.value());
        }
        throw new CustomException("Text not found", true, HttpStatus.NOT_FOUND.value());
    }

}
