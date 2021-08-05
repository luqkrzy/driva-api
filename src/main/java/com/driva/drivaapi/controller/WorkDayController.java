package com.driva.drivaapi.controller;

import com.driva.drivaapi.mapper.dto.WorkDayDTO;
import com.driva.drivaapi.service.WorkDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/workdays")
public class WorkDayController {
   
   private final WorkDayService workdayService;
   
   @GetMapping
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   List<WorkDayDTO> getAllWorkDays() {
      return workdayService.findAll();
   }
   
   @GetMapping("/{id}")
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   WorkDayDTO getWorkDay(@PathVariable Long id) {
      return workdayService.findToDTO(id);
   }
   
   @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
           produces = MediaType.APPLICATION_JSON_VALUE)
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   @ResponseStatus(code = HttpStatus.CREATED)
   WorkDayDTO createWorkDay(@RequestBody @Valid WorkDayDTO workday) {
      return workdayService.save(workday);
   }
   
   @PutMapping
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   @ResponseStatus(code = HttpStatus.OK)
   WorkDayDTO updateWorkDay(@RequestBody @Valid WorkDayDTO workday) {
      return workdayService.update(workday);
   }
   
   @DeleteMapping("/{id}")
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   void deleteWorkDay(@PathVariable Long id) {
      workdayService.delete(id);
   }
}
