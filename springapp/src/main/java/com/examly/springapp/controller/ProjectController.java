package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Project;

import com.examly.springapp.repository.ProjectRepo;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectRepo repo;

   @PostMapping
   public ResponseEntity<Project> addProject(@RequestBody Project project){
    Project savedProject= repo.save(project);
    return new ResponseEntity<Project>(savedProject,HttpStatus.CREATED);
   }
   @GetMapping
   public List<Project> getAllProjects(){
    return repo.findAll();
   }
   @GetMapping("/status/{status}")
   public ResponseEntity<?> getProjectsByStatus(@PathVariable String status){
    List<Project> projects = repo.findByStatus(status);

    if(projects.isEmpty()){
      return new ResponseEntity<>("No projects found with status: " + status,HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Project>>(projects,HttpStatus.OK);
   }

   @GetMapping("/{id}")
   public Project getProjectbyId(@PathVariable int id){
    return repo.findById(id).orElse(null);
   }
   @PutMapping("/{id}")
   public Project updateProject(@PathVariable int id,@RequestBody Project project){
    Project existing = repo.findById(id).orElse(null);
    if(existing != null){
      existing.setProjectName(project.getProjectName());
      existing.setDescription(project.getDescription());
      existing.setStatus(project.getStatus());
      return repo.save(existing);
    }
return null;

   }


}
