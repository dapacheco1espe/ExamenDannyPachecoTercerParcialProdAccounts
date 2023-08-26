package com.banquito.core.productsaccounts;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.banquito.core.productsaccounts.model.InterestRate;
import com.banquito.core.productsaccounts.repository.InterestRateRepository;
import com.banquito.core.productsaccounts.service.InterestRateService;
public class InterestRateServiceTest {
    private InterestRateService interestRateService;
    private InterestRateRepository interestRateRepository;
    @BeforeEach
    public void setUp(){
        this.interestRateRepository = Mockito.mock(InterestRateRepository.class);
        this.interestRateService = new InterestRateService(interestRateRepository);
    }
    @Test
    public void shouldListAllActivesInterests(){
        //AAA- ARRANGE-ACT-ASSERT
        InterestRate interes = new InterestRate();
        interes.setId(1);
        interes.setInterestRate(new BigDecimal(0));
        interes.setState("ACT");
        interes.setStart(new Date());
        interes.setEnd(null);
        InterestRate interes2 = new InterestRate();
        interes2.setId(1);
        interes2.setInterestRate(new BigDecimal(0));
        interes2.setState("ACT");
        interes2.setStart(new Date());
        interes2.setEnd(null);
        when(this.interestRateRepository.findByState(Mockito.any(String.class))).thenReturn(List.of(interes,interes2));
        List<InterestRate> mockedListInterest = this.interestRateService.listAllActives();
        assertEquals(mockedListInterest.size(),2);
        assertThat(mockedListInterest).isNotNull();
    }
    @Test
    public void shouldReturnInteresRateById(){
         //AAA- ARRANGE-ACT-ASSERT
        InterestRate interes = new InterestRate();
        interes.setId(1);
        interes.setInterestRate(new BigDecimal(0));
        interes.setState("ACT");
        interes.setStart(new Date());
        interes.setEnd(null);
        when(this.interestRateRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(interes));
        InterestRate mockedInterest = this.interestRateService.obtainById(1);
        assertEquals(mockedInterest,interes);
    }

    @Test
    public void shouldCreateInterestWithoutException(){
        //AAA- ARRANGE-ACT-ASSERT
        InterestRate interes = new InterestRate();
        interes.setId(1);
        interes.setInterestRate(new BigDecimal(0));
        interes.setState("ACT");
        interes.setStart(new Date());
        interes.setEnd(null);
        doNothing().when(this.interestRateRepository.save(Mockito.any(InterestRate.class)));
        Mockito.verify(this.interestRateRepository, times(1)).save(interes);
    }

    @Test
    public void shouldUpdateInterestRateByIdAndObjInterestRate(){
        //AAA- ARRANGE-ACT-ASSERT
        Integer id  = 1;
        InterestRate interes = new InterestRate();
        interes.setId(1);
        interes.setInterestRate(new BigDecimal(0));
        interes.setState("ACT");
        interes.setStart(new Date());
        interes.setEnd(null);
        doNothing().when(this.interestRateRepository.findById(Mockito.any(Integer.class)));
        doNothing().when(this.interestRateRepository.save(Mockito.any(InterestRate.class)));
        Mockito.verify(this.interestRateRepository, times(1)).findById(id);
        Mockito.verify(this.interestRateRepository, times(1)).save(interes);
    }
}
