package com.mediscreen.proxies;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mediscreen.model.Note;

@FeignClient(name = "micro-note")
@RibbonClient(name = "micro-note")
public interface NoteProxy {

	@GetMapping("note/listNote/{patientId}")
	List<Note> listNotes(@PathVariable(value = "patientId") int patientId);

	@PostMapping("note/addNote")
	Note addNote(@RequestBody Note note);

	@GetMapping("note/getNote/{noteId}")
	Note getNote(@PathVariable(value = "noteId") String noteId);

}
