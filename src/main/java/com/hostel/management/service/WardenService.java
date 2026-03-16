package com.hostel.management.service;
import com.hostel.management.dto.request.WardenRequest;
import com.hostel.management.dto.response.WardenResponse;
import java.util.List;
public interface WardenService {
    WardenResponse createWarden(WardenRequest request);
    WardenResponse updateWarden(Long wardenId, WardenRequest request);
    void deleteWarden(Long wardenId);
    WardenResponse getWardenById(Long wardenId);
    List<WardenResponse> getAllWardens();
}
