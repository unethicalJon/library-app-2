package com.example.library.controller;

import com.example.library.dto.general.EntityIdDto;
import com.example.library.dto.library.LibraryDto;
import com.example.library.entity.Library;
import com.example.library.service.LibraryService;
import com.example.library.util.constants.RestConstants;
import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(RestConstants.LibraryController.BASE)
public class LibraryController {

    private final LibraryService libraryService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping(RestConstants.LibraryController.ADD)
    public ResponseEntity<EntityIdDto> registerLibrary(@RequestBody LibraryDto addLibraryRequest) {
        Library library = libraryService.registerLibrary(addLibraryRequest);
        return new ResponseEntity<>(EntityIdDto.of(library.getId()), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping(RestConstants.LibraryController.UPDATE)
    public ResponseEntity<Library> updateLibrary(@PathVariable(value = RestConstants.ID) Long id, @RequestBody LibraryDto updatedLibrary) {
        Library library = libraryService.updateLibrary(id, updatedLibrary);
        return new ResponseEntity(EntityIdDto.of(library.getId()), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping(RestConstants.LibraryController.DELETE)
    public String deleteLibrary(@PathVariable(value = RestConstants.ID) Long id) {
        try {
            return libraryService.deleteLibrary(id);
        } catch (DataIntegrityViolationException ex){
            throw new RuntimeException("Libraria me id: " + id + " eshte e lidhur me entitete te tjera!");
        }
    }

/*    @GetMapping(RestConstants.LibraryController.LIBRARIES)
    public List<LibraryDto> getLibraries(Pageable pageable) {
        return libraryService.getLibraries(pageable);
    }*/

/*    @GetMapping(RestConstants.LibraryController.LIBRARIES)
    public List<LibraryDto> getLibraries(@RequestParam(required = false) String name,
                                         @RequestParam(defaultValue = RestConstants.DEFAULT_PAGE_NUMBER) int page,
                                         @RequestParam(defaultValue = RestConstants.DEFAULT_PAGE_SIZE) int size) {
        return libraryService.getLibraries(name, page, size);
    }*/

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Page<LibraryDto> getLibrariesRoleAdmin(@RequestParam(required = false) String name,
                                                  @RequestParam(required = false) String address,
                                                  @RequestParam(defaultValue = RestConstants.DEFAULT_PAGE_NUMBER) int page,
                                                  @RequestParam(defaultValue = RestConstants.DEFAULT_PAGE_SIZE) int size) {
        return libraryService.getLibrariesRoleAdmin(name, address, page, size);
    }

    @GetMapping(RestConstants.LibraryController.LIBRARIES_FOR_USER)
    @PreAuthorize("hasAnyAuthority('USER')")
    public LibraryDto getLibraryRoleUser() {
        return libraryService.getLibraryRoleUser();
    }

    @GetMapping(RestConstants.LibraryController.SEARCH)
    public Page<LibraryDto> searchLibraries(@RequestParam String name, Pageable pageable) {
        return libraryService.searchByName(name, pageable);
    }
}
