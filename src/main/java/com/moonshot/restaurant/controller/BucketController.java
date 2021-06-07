package com.moonshot.restaurant.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.moonshot.restaurant.service.AmazonClient;

@RestController
@RequestMapping("/storage/")
public class BucketController {

    private AmazonClient amazonClient;

    @Autowired
    BucketController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    @RequestMapping(method=RequestMethod.POST, value="/files")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file, @RequestParam(name = "prefix") String prefix) {
        return this.amazonClient.uploadFile(file, prefix+"-");
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/formfiles")
    public String uploadFile1(@RequestParam("file") MultipartFile file, @RequestParam(name = "prefix") String prefix) {
        return this.amazonClient.uploadFile(file, prefix+"-");
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/files")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
    }
}
