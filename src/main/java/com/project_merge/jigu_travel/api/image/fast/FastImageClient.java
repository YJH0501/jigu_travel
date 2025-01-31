package com.project_merge.jigu_travel.api.image.fast;

import com.project_merge.jigu_travel.api.image.dto.ImageResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * 📌 Feign을 사용한 FastAPI 호출 클라이언트
 */
@FeignClient(name = "fastImageClient", url = "${fast-api.host}")
public interface FastImageClient {

    @PostMapping(value = "/image_search", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ImageResponseDto uploadImage(@RequestPart("file") MultipartFile file);
}
