package com.nbu.java.practice.learningprocessorganizer.util;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class GenericModelMapper<T, S> {

    private final ModelMapper modelMapper;

    public T map(S source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }

    public List<T> map(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

}
