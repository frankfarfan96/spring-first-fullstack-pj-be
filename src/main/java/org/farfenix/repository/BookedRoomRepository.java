package org.farfenix.repository;

import org.farfenix.model.BookedRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookedRoomRepository extends JpaRepository<BookedRoom, Long> {
}
