package br.com.furb.controller.impl;

import br.com.furb.controller.ApiRequestController;
import br.com.furb.model.ApiRequest;
import br.com.furb.repository.ApiRequestRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ApiRequestControllerImpl implements ApiRequestController {

    @Autowired
    private ApiRequestRepository repository;

    public void create(ApiRequest apiRequest) {
        repository.create(apiRequest);
    }

    public void update(ApiRequest apiRequest) {
        repository.update(apiRequest);
    }

    public void delete(Long id) {
        repository.delete(id);
    }

    public ApiRequest find(Long id) {
        return repository.find(id);
    }

    public List<ApiRequest> findAll() {
        return repository.findAll();
    }

}
