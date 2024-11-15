package com.keshav.Job.App.Rest;
import com.keshav.Job.App.Rest.model.JobPost;
import com.keshav.Job.App.Rest.service.JobService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class JobRestController {

    @Autowired
    private JobService service;

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping(path = "jobPosts" , produces = {"application/json"})
    public List<JobPost> getAllJobs(){
        return service.getAllJob();
    }

    @GetMapping("jobPosts/{postId}")
    public JobPost getJob(@PathVariable("postId")int postId){
        return service.getPost(postId);
    }

    @GetMapping("jobPosts/keyword/{keyword}")
    public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword){
        return service.search(keyword);
    }

    @PostMapping(path="jobPosts",consumes = {"application/json"})
    public JobPost addPost(@RequestBody JobPost jobPost ){
        service.addJob(jobPost);
        return service.getPost(jobPost.getPostId());
    }
    @PutMapping("jobPosts") // PutMapping for update
    public JobPost updateJob(@RequestBody JobPost jobPost){
      service.updateJob(jobPost);
      return service.getPost(jobPost.getPostId());
    };
    @DeleteMapping("jobPosts/{postId}")
    public String deleteJob(@PathVariable int postId){
        service.deleteJob(postId);
        return "Deleted";
    }
    @GetMapping("load")

    public String loadData(){
        service.load();
        return "Success";
    }

}
