package com.in28minutes.springboot.tutorial.basics.application.configuration.service;

import java.util.List;
import java.util.Optional;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.in28minutes.springboot.tutorial.basics.application.configuration.model.MediaType;
import com.in28minutes.springboot.tutorial.basics.application.configuration.repository.MediaTypeRepository;

@Service
public class MediaTypeService implements IMediaTypeService {
    @Autowired
    private MediaTypeRepository mediaTypeRepository;

    public MediaTypeService(MediaTypeRepository mediaTypeRepository) {
        this.mediaTypeRepository = mediaTypeRepository;
    }

    @Override
    public List<MediaType> getMediaTypes() {
        return mediaTypeRepository.findAll();
    }

    @Override
    public Optional<MediaType> getMediaTypeById(long id) {
        return mediaTypeRepository.findById(id);
    }

    @Override
    public void updateMediaType(MediaType mediaType) {
        mediaTypeRepository.save(mediaType);
    }

    @Override
    public void addMediaType(MediaType mediaType) {

        mediaTypeRepository.save(mediaType);
    }

    @Override
    public void deleteMediaType(long id) {
        mediaTypeRepository.deleteById(id);
    }

    @Override
    public void saveMediaType(MediaType mediaType) {
        mediaTypeRepository.save(mediaType);
    }

}
