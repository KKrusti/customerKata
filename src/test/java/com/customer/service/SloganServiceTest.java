package com.customer.service;

import com.customer.data.TestData;
import com.customer.entity.SloganEntity;
import com.customer.exceptions.MaximumSlogansException;
import com.customer.repository.SloganRepository;
import com.customer.service.mapper.SloganEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class SloganServiceTest {

    private SloganService sloganService;

    @Mock
    private SloganEntityMapper sloganEntityMapper;
    @Mock
    private SloganRepository sloganRepository;

    @BeforeEach
    void setUp() {
        sloganService = new SloganService(sloganRepository, sloganEntityMapper);
    }

    @Test
    void should_upload_a_slogan() {
        var customerId = 1L;
        var slogan = TestData.getSlogan();
        var expectedSlogan = TestData.getSlogan(1L);
        SloganEntity sloganEntity = TestData.getSloganEntity();
        when(sloganRepository.findByCustomerId(customerId)).thenReturn(Optional.empty());
        when(sloganRepository.save(any(SloganEntity.class))).thenReturn(sloganEntity);
        when(sloganEntityMapper.toDomain(any(SloganEntity.class))).thenReturn(expectedSlogan);
        when(sloganEntityMapper.toEntity(slogan)).thenReturn(sloganEntity);

        var response = sloganService.uploadSlogan(slogan);

        verify(sloganRepository, times(1)).findByCustomerId(customerId);
        verify(sloganRepository, times(1)).save(sloganEntity);
        assertEquals(expectedSlogan, response);
    }

    @Test
    void should_throw_maximum_slogans_exception_on_fourth_slogan() {
        var customerId = 1L;
        var slogan = TestData.getSlogan();
        var SloganEntities = Arrays.asList(new SloganEntity(), new SloganEntity(), new SloganEntity());
        when(sloganRepository.findByCustomerId(customerId)).thenReturn(Optional.of(SloganEntities));

        assertThrows(MaximumSlogansException.class,
                () -> sloganService.uploadSlogan(slogan));
    }
}
