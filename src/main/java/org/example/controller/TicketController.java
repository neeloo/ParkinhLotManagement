package org.example.controller;


import org.example.models.IssueTicketRequestDTO;
import org.example.models.IssueTicketResponseDTO;
import org.example.models.ResponseStatus;
import org.example.models.Ticket;
import org.example.repositories.GateRepository;
import org.example.repositories.TicketRepository;
import org.example.repositories.VehicleRepository;
import org.example.services.TicketService;

public class TicketController {
    TicketService ticketService;

    public TicketController(GateRepository gateRepository, TicketRepository ticketRepository, VehicleRepository vehicleRepository) {
        this.ticketService = new TicketService(gateRepository, vehicleRepository, ticketRepository);
    }

    public IssueTicketResponseDTO issueTicket(IssueTicketRequestDTO requestDTO){
        IssueTicketResponseDTO responseDTO = new IssueTicketResponseDTO();
        // Issue Ticket service with required details
        try{
            Ticket ticket = ticketService.issueTicket(requestDTO.getReg_number()
                    ,requestDTO.getOwner_name()
                    , requestDTO.getOwner_number()
                    ,requestDTO.getVehicle_type()
                    ,requestDTO.getGate_id());

            responseDTO.setResponseMessage("Ticket created successfully");
            responseDTO.setStatus(ResponseStatus.SUCCESS);
            responseDTO.setTicket_number(ticket.getNumber());
            responseDTO.setSlot_number(ticket.getParkingSlot().getNumber());
        }catch(Exception e){
            responseDTO.setResponseMessage("Failed to create Ticket");
            responseDTO.setStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }
}