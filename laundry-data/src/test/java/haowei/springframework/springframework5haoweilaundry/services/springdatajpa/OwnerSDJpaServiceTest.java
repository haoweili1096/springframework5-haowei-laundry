package haowei.springframework.springframework5haoweilaundry.services.springdatajpa;

import haowei.springframework.springframework5haoweilaundry.model.Owner;
import haowei.springframework.springframework5haoweilaundry.repositories.ClothRepository;
import haowei.springframework.springframework5haoweilaundry.repositories.ClothTypeRepository;
import haowei.springframework.springframework5haoweilaundry.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OwnerSDJpaServiceTest {

    public static final String LAST_NAME = "Smith";
    @Mock
    OwnerRepository ownerRepository;

    @Mock
    ClothRepository clothRepository;

    @Mock
    ClothTypeRepository clothTypeRepository;

    @InjectMocks
    OwnerSDJpaService ownerSDJpaService;

    Owner owner;

    @BeforeEach
    void setUp(){
        owner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() throws Exception{
        when(ownerRepository.findByLastName(any())).thenReturn(owner);

        Owner smith = ownerSDJpaService.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, smith.getLastName());

        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAllByLastNameLike() throws Exception{
        when(ownerRepository.findAllByLastNameLike(any())).thenReturn(Arrays.asList(owner));

        List<Owner> ownerList = ownerSDJpaService.findAllByLastNameLike(LAST_NAME);

        assertEquals(LAST_NAME, ownerList.get(0).getLastName());

        verify(ownerRepository).findAllByLastNameLike(any());
    }

    @Test
    void findAll() throws Exception{
        Set<Owner> owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(owners);

        Set<Owner> allOwners = ownerSDJpaService.findAll();

        assertNotNull(allOwners);
        assertEquals(2, allOwners.size());
    }

    @Test
    void findByIdFound() throws Exception{
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner));// since in the method I return .orElse(null) for Owner

        Owner target = ownerSDJpaService.findById(1L);

        assertNotNull(target);

    }

    @Test
    void findByIdNotFound() throws Exception{
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner target = ownerSDJpaService.findById(1L);

        assertNull(target);
    }

    @Test
    void save() throws Exception{
        Owner ownerToSave = Owner.builder().id(1L).build();

        when(ownerRepository.save(any())).thenReturn(owner);

        Owner savedOwner = ownerSDJpaService.save(ownerToSave);

        assertNotNull(savedOwner);

        verify(ownerRepository).save(any());
    }

    @Test
    void delete() throws Exception{
        ownerSDJpaService.delete(owner);

        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() throws Exception{
        ownerSDJpaService.deleteById(1L);

        verify(ownerRepository, times(1)).deleteById(anyLong());
    }
}
