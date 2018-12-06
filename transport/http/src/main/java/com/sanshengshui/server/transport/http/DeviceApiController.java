package com.sanshengshui.server.transport.http;

import com.google.gson.JsonParser;
import com.sanshengshui.server.common.data.kv.AttributeKvEntry;
import com.sanshengshui.server.common.transport.adaptor.JsonConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Set;

/**
 * @author james mu
 */
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class DeviceApiController {

    @RequestMapping(value = "/{deviceToken}/attributes",method = RequestMethod.POST)
    public DeferredResult<ResponseEntity> postDeviceAttributes(
            @PathVariable("deviceToken") String deviceToken,
            @RequestBody String json) {
        DeferredResult<ResponseEntity> responseWriter = new DeferredResult<ResponseEntity>();
        responseWriter.setResult(new ResponseEntity<>(HttpStatus.ACCEPTED));
        Set<AttributeKvEntry> attributeKvEntrySet = JsonConverter.convertToAttributes(new JsonParser().parse(json)).getAttributes();
        for (AttributeKvEntry attributeKvEntry : attributeKvEntrySet){
            System.out.println("属性名="+attributeKvEntry.getKey()+" 属性值="+attributeKvEntry.getValueAsString());
        }
        return responseWriter;
    }
}
