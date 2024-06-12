package org.farfenix.service;

import org.farfenix.model.BookedRoom;

import java.util.List;

public interface IBookingService {
    List<BookedRoom> getAllBookingsByRoomId(Long roomId);
}
