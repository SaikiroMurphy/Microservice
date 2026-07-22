package re.edu.pharmacy_service.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import re.edu.pharmacy_service.model.entity.*;
import re.edu.pharmacy_service.model.mapper.BillDetailMapper;
import re.edu.pharmacy_service.model.mapper.BillMapper;
import re.edu.pharmacy_service.event.OrderEvent;
import re.edu.pharmacy_service.kafka.OrderProducer;
import re.edu.pharmacy_service.model.dto.request.*;
import re.edu.pharmacy_service.model.dto.response.*;
import re.edu.pharmacy_service.repository.BillDetailRepository;
import re.edu.pharmacy_service.repository.BillRepository;
import re.edu.pharmacy_service.utils.BillStatus;

@Service
@RequiredArgsConstructor
@Transactional
public class BillService {

    private final BillRepository billRepository;
    private final BillMapper billMapper;

    private final BillDetailRepository billDetailRepository;
    private final BillDetailMapper billDetailMapper;

    private final OrderProducer orderProducer;

    @Value("${pharmacy.vat-rate}")
    private BigDecimal vatRate;

    public BillResponse create(CreateBillRequest request) {

        Bill bill = billMapper.toEntity(request);

        bill.setTotalAmount(sumTotalAmount(request.getBillDetails()));
        bill.setStatus(BillStatus.PENDING);

        Bill savedBill = billRepository.save(bill);

        List<BillDetail> details = request.getBillDetails().stream()
                .map(billDetailMapper::toEntity)
                .peek(detail -> detail.setBill(savedBill))
                .peek(detail -> detail.setSubtotal(detail.getUnitPrice().multiply(BigDecimal.valueOf(detail.getQuantity()))))
                .toList();

        billDetailRepository.saveAll(details);

        BillResponse response = billMapper.toResponse(savedBill);
        response.setBillDetails(details.stream()
                .map(billDetailMapper::toResponse)
                .toList());
        response.setVatRate(vatRate);
        response.setVatAmount(response.getTotalAmount().multiply(vatRate));
        response.setFinalAmount(response.getTotalAmount().add(response.getVatAmount()));

        // Gửi sự kiện đến Kafka
        details.forEach(detail -> {
            OrderEvent event = OrderEvent.builder()
                    .orderId(savedBill.getId())
                    .medicineId(detail.getProductId())
                    .quantity(detail.getQuantity())
                    .timestamp(savedBill.getCreatedAt())
                    .build();

            orderProducer.send(event);
        });

        return response;
    }

    @Transactional(readOnly = true)
    public List<BillResponse> findAll() {
        // TODO: Xử lý logic

        return billRepository.findAll()
                .stream()
                .map(billMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public BillResponse findById(Long id) {
        // TODO: Xử lý logic

        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        return billMapper.toResponse(bill);
    }

    public BillResponse update(Long id, UpdateBillRequest request) {
        // TODO: Xử lý logic

        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        billMapper.update(request, bill);

        return billMapper.toResponse(billRepository.save(bill));
    }

    public void delete(Long id) {
        // TODO: Xử lý logic

        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        billRepository.delete(bill);
    }

    public BigDecimal sumTotalAmount(List<CreateBillDetailRequest> billDetails) {
        return billDetails.stream()
                .map(detail -> detail.getUnitPrice().multiply(BigDecimal.valueOf(detail.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
