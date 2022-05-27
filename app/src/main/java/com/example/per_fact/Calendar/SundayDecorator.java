package com.example.per_fact.Calendar;

import android.graphics.Color;
import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import org.threeten.bp.DayOfWeek;

import java.util.Calendar;


//일요일 빨간색으로 만드는 클래스
public class SundayDecorator implements DayViewDecorator {

    private final Calendar calendar = Calendar.getInstance();

    public SundayDecorator() {
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
//        day.copyTo(calendar);
//        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
//        return weekDay == Calendar.SUNDAY;

        int sunday = day.getDate().with(DayOfWeek.SUNDAY).getDayOfMonth();
        return sunday == day.getDay();
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(Color.RED));
    }
}
