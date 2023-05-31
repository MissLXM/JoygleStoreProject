package cn.edu.mju.joygle.common.client.statics;

import cn.edu.mju.joygle.common.core.domain.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName: StaticClient
 * Package: cn.edu.mju.joygle.common.client.statics
 * Description: 静态资源客户端
 *
 * @Author:wjh
 * @Create:2023-05-2023/5/30--13:49
 */
@FeignClient("static-service")
public interface StaticClient {

    @PostMapping(value = "/static/downloadAvatar",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    Result downloadAvatar(@RequestPart("avatar") MultipartFile avatarFile);
}
