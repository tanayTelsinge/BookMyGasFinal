package com.cdac.bookmygas.unittest;

import com.app.bookmygas.controller.AgencyController;
import com.app.bookmygas.controller.ConnectionController;
import com.app.bookmygas.controller.OrderController;
import com.app.bookmygas.controller.PaymentController;
import com.app.bookmygas.controller.StaffController;
import com.app.bookmygas.controller.StockController;
import com.app.bookmygas.controller.UserController;
import com.app.bookmygas.controller.VendorController;
import com.app.bookmygas.entity.*;
import com.app.bookmygas.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CombinedControllerTests {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private VendorService vendorService;

    @Mock
    private AgencyService agencyService;

    @Mock
    private StaffService staffService;

    @Mock
    private OrderService orderService;

    @Mock
    private ConnectionService connectionRequestService;

    @Mock
    private PaymentService paymentService;

    @Mock
    private StockService stockService;

    @InjectMocks
    private UserController userController;

    @InjectMocks
    private VendorController vendorController;

    @InjectMocks
    private AgencyController agencyController;

    @InjectMocks
    private StaffController staffController;

    @InjectMocks
    private OrderController orderController;

    @InjectMocks
    private ConnectionController connectionRequestController;

    @InjectMocks
    private PaymentController paymentController;

    @InjectMocks
    private StockController stockController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController, vendorController, agencyController,
                        staffController, orderController, connectionRequestController,
                        paymentController, stockController)
                .build();
    }

    @Test
    void createUser() throws Exception {
        User user = new User();
        user.setUserId(1);
        when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").value(1));
    }

    @Test
    void getUserById() throws Exception {
        User user = new User();
        user.setUserId(1);
        when(userService.getUserById(anyInt())).thenReturn(user);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1));
    }

    @Test
    void createVendor() throws Exception {
        Vendor vendor = new Vendor();
        vendor.setVendorId(1);
        when(vendorService.createVendor(any(Vendor.class))).thenReturn(vendor);

        mockMvc.perform(post("/api/vendors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(vendor)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.vendorId").value(1));
    }

    @Test
    void getVendorById() throws Exception {
        Vendor vendor = new Vendor();
        vendor.setVendorId(1);
        when(vendorService.getVendorById(anyInt())).thenReturn(vendor);

        mockMvc.perform(get("/vendors/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendorId").value(1));
    }

    @Test
    void createAgency() throws Exception {
    	GasAgency agency = new GasAgency();
        agency.setAgencyId(1);
        when(agencyService.createAgency(any(GasAgency.class))).thenReturn(agency);

        mockMvc.perform(post("/api/agencies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(agency)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.agencyId").value(1));
    }

    @Test
    void getAgencyById() throws Exception {
        GasAgency agency = new GasAgency();
        agency.setAgencyId(1);
        when(agencyService.getAgencyById(anyInt())).thenReturn(agency);

        mockMvc.perform(get("/agencies/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.agencyId").value(1));
    }

    @Test
    void createStaff() throws Exception {
        Staff staff = new Staff();
        staff.setStaffId(1);
        when(staffService.createStaff(any(Staff.class))).thenReturn(staff);

        mockMvc.perform(post("/staff")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(staff)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.staffId").value(1));
    }

    @Test
    void getStaffById() throws Exception {
        Staff staff = new Staff();
        staff.setStaffId(1);
        when(staffService.getStaffById(anyInt())).thenReturn(staff);

        mockMvc.perform(get("/staff/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.staffId").value(1));
    }

    @Test
    void createOrder() throws Exception {
        Order order = new Order();
        order.setOrderId(1);
        when(orderService.createOrder(any(Order.class))).thenReturn(order);

        mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(order)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.orderId").value(1));
    }

    @Test
    void getOrderById() throws Exception {
        Order order = new Order();
        order.setOrderId(1);
        when(orderService.getOrderById(anyInt())).thenReturn(order);

        mockMvc.perform(get("/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(1));
    }

    @Test
    void createConnectionRequest() throws Exception {
        ConnectionRequest request = new ConnectionRequest();
        request.setRequestId(1);
        when(connectionRequestService.createConnectionRequest(any(ConnectionRequest.class))).thenReturn(request);

        mockMvc.perform(post("/api/connection-requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.requestId").value(1));
    }

    @Test
    void getConnectionRequestById() throws Exception {
        ConnectionRequest request = new ConnectionRequest();
        request.setRequestId(1);
        when(connectionRequestService.getConnectionRequestById(anyInt())).thenReturn(request);

        mockMvc.perform(get("/connections/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.requestId").value(1));
    }

    @Test
    void createPayment() throws Exception {
        Payment payment = new Payment();
        payment.setPaymentId(1);
        when(paymentService.createPayment(any(Payment.class))).thenReturn(payment);

        mockMvc.perform(post("/api/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(payment)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.paymentId").value(1));
    }

    @Test
    void getPaymentById() throws Exception {
        Payment payment = new Payment();
        payment.setPaymentId(1);
        when(paymentService.getPaymentById(anyInt())).thenReturn(payment);

        mockMvc.perform(get("/payments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paymentId").value(1));
    }

    @Test
    void createStock() throws Exception {
        Stock stock = new Stock();
        stock.setStockId(1);
        when(stockService.createStock(any(Stock.class))).thenReturn(stock);

        mockMvc.perform(post("/api/stocks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(stock)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.stockId").value(1));
    }

    @Test
    void getStockById() throws Exception {
        Stock stock = new Stock();
        stock.setStockId(1);
        when(stockService.getStockById(anyInt())).thenReturn(stock);

        mockMvc.perform(get("/stocks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stockId").value(1));
    }
}
