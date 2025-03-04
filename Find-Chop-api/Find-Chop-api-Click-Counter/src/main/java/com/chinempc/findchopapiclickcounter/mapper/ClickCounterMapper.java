package com.chinempc.findchopapiclickcounter.mapper;

import com.chinempc.findchopapiclickcounter.dto.ResponseDTO;
import com.chinempc.findchopapiclickcounter.model.Click;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClickCounterMapper {
    // POJO -> DTO

    // DTO -> POJO
    public ResponseDTO singleToDTO(Click click) {
        return new ResponseDTO(
                click.getRecipeName()
        );
    }

    public List<ResponseDTO> multiToDTO(List<Click> clicks) {
        return clicks.stream()
                .map(this::singleToDTO)
                .collect(Collectors.toList());
    }
}
