package org.farfenix.repository;

import org.farfenix.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT DISTINCT r.roomType FROM Room r")
    List<String> findDistinctRoomTypes();

    @Query("SELECT r FROM Room r " +
            "WHERE r.roomType LIKE %:roomType% " +
            " AND r.id NOT IN (" +
            " SELECT br.room.id FROM BookedRoom br " +
            " WHERE ((br.checkInDate <= :checkOutDate) AND (br.checkOutDate >= :checkInDate))" +
            ")")
    List<Room> findAvailableRoomsByDatesAndType(LocalDateTime checkInDate, LocalDateTime checkOutDate, String roomType);
}
