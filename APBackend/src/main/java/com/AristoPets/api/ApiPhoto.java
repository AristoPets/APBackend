package com.AristoPets.api;

import com.AristoPets.aws.AmazonS3UploadService;
import com.AristoPets.aws.BucketNames;
import com.AristoPets.dto.PhotoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/photo")
public class ApiPhoto {

    private final AmazonS3UploadService s3Handler;

    @Autowired
    public ApiPhoto(AmazonS3UploadService s3Handler) {
        this.s3Handler = s3Handler;
    }


    @PostMapping("/animal/")
    public ResponseEntity<String> uploadAnimalPhoto(@RequestBody PhotoDto photoDto) {
        String imageUrl = s3Handler.uploadImage(photoDto.getPhoto(), BucketNames.ANIMAL_PHOTO);

        return ResponseEntity.ok(imageUrl);
    }

    @PostMapping("/advert/")
    public ResponseEntity<String> uploadAdvertPhoto(@RequestBody PhotoDto photoDto) {
        String imageUrl = s3Handler.uploadImage(photoDto.getPhoto(), BucketNames.ADVERT_PHOTO);

        return ResponseEntity.ok(imageUrl);
    }


    @PostMapping("/user/")
    public ResponseEntity<String> uploadUserPhoto(@RequestBody PhotoDto photoDto) {
        String imageUrl = s3Handler.uploadImage(photoDto.getPhoto(), BucketNames.USER_PHOTO);

        return ResponseEntity.ok(imageUrl);
    }

}
