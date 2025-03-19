package com.in28minutes.springboot.tutorial.basics.application.configuration.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.in28minutes.springboot.tutorial.basics.application.configuration.model.MediaType;

public interface IMediaTypeService {
    List<MediaType> getMediaTypes();

    Optional<MediaType> getMediaTypeById(long id);

    void updateMediaType(MediaType mediaType);

    void addMediaType(MediaType mediaType);

    void deleteMediaType(long id);

    void saveMediaType(MediaType mediaType);
}
