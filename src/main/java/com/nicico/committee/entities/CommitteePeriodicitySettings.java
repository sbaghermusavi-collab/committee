package com.nicico.committee.entities;

import com.nicico.copper.common.domain.Auditable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

/**
 * Stores the detailed periodicity settings for a committee calendar.
 */
@Entity
@Table(name = "committee_periodicity_settings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommitteePeriodicitySettings extends Auditable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "uuid")
    @Schema(description = "شناسه تنظیمات تناوب کمیته")
    private String id;

    // ----- Calendar FK (one-to-one owner) -----
    @Column(name = "calendar_id", unique = true, nullable = false)
    @Schema(description = "شناسه تقویم کمیته")
    private String calendarId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id", insertable = false, updatable = false)
    @Schema(description = "تقویم کمیته")
    private CommitteeCalendar calendar;

    // ----- IntervalValue -----
    @Column(name = "interval_value")
    @Schema(description = "مقدار فاصله بین جلسات (مثلاً هر ۲ واحد دوره)")
    private Integer intervalValue;

    // ----- WeekdayMask -----
    @Column(name = "weekday_mask")
    @Schema(description = "روزهای هفته برگزاری (ماسک روزها)")
    private String weekdayMask;

    // ----- DayOfMonth -----
    @Column(name = "day_of_month")
    @Schema(description = "روز ماه برگزاری جلسه")
    private String dayOfMonth;

    // ----- WeekOfMonth -----
    @Column(name = "week_of_month")
    @Schema(description = "هفته ماه (اول، دوم، ...)")
    private String weekOfMonth;

    // ----- MonthMask -----
    @Column(name = "month_mask")
    @Schema(description = "ماه‌های برگزاری (ماسک ماه‌ها)")
    private String monthMask;

    // ----- HolidayRule -----
    @Column(name = "holiday_rule")
    @Schema(description = "قانون برخورد با ایام تعطیل (مثلاً جابجایی به روز قبل/بعد)")
    private String holidayRule;
}