import { shallowMount } from "@vue/test-utils";
import { describe, it, expect, beforeEach, afterEach, vi } from "vitest";
import BookingWorkStationDelete from "../../views/business/bookings/BusinessBookingsDeleteView.vue";

describe("BookingWorkStationDelete", () => {
  let wrapper;

  beforeEach(() => {
    wrapper = shallowMount(BookingWorkStationDelete, {
      global: {
        mocks: {
          $ApiService: {
            find_user_by_id: vi.fn(() =>
              Promise.resolve({ data: { companyId: "company-id" } }),
            ),
            get_booking_businesses_by_user_id: vi.fn(() =>
              Promise.resolve({ data: [] }),
            ),
            get_bookings_by_business_booking_id: vi.fn(() =>
              Promise.resolve({ data: [] }),
            ),
            delete_booking: vi.fn(() =>
              Promise.resolve("Booking deleted successfully"),
            ),
          },
        },
      },
    });

    // Mock current date
    vi.setSystemTime(new Date("2023-05-25T08:00:00Z"));
  });

  afterEach(() => {
    wrapper.unmount();
    vi.useRealTimers(); // Reset mock date
  });

  it("fetchCompanyId should set the company ID correctly", async () => {
    await wrapper.vm.fetchCompanyId();
    expect(wrapper.vm.companyId).toBe("company-id");
  });

  it("deleteItem should call delete_booking API method and update bookings", async () => {
    wrapper.vm.$ApiService.delete_booking = vi.fn(() =>
      Promise.resolve("Booking deleted successfully"),
    );

    await wrapper.vm.deleteItem({ status: "active", bookingId: "booking-id" });

    expect(wrapper.vm.$ApiService.delete_booking).toHaveBeenCalledWith(
      "booking-id",
    );
  });

  it("formatDate should format the date correctly", () => {
    const date = "2024-04-07T12:30:00Z";
    const formattedDate = wrapper.vm.formatDate(date);
    expect(formattedDate).toBe("04-07-2024");
  });

  it("confirmStartDate should set the start date and formatted start date correctly", () => {
    wrapper.vm.tempStartDate = "2024-04-07";
    wrapper.vm.confirmStartDate();
    expect(wrapper.vm.startDate).toBe("2024-04-07");
    expect(wrapper.vm.formattedStartDate).toBe("04-07-2024");
  });

  it("confirmEndDate should set the end date and formatted end date correctly", () => {
    wrapper.vm.tempEndDate = "2024-04-07";
    wrapper.vm.confirmEndDate();
    expect(wrapper.vm.endDate).toBe("2024-04-07");
    expect(wrapper.vm.formattedEndDate).toBe("04-07-2024");
  });

  it("clearFilters should reset all filters and re-initialize the table", async () => {
    wrapper.vm.clearFilters();
    expect(wrapper.vm.startDate).toBeNull();
    expect(wrapper.vm.endDate).toBeNull();
    expect(wrapper.vm.formattedStartDate).toBe("");
    expect(wrapper.vm.formattedEndDate).toBe("");
  });

  it("getStatusColor should return the correct color based on status and date", () => {
    const activeBooking = {
      status: "active",
      startDate: "2024-04-07T09:00:00Z",
    };
    const canceledBooking = {
      status: "canceled",
      startDate: "2024-04-07T09:00:00Z",
    };
    const pastBooking = { status: "active", startDate: "2023-04-07T09:00:00Z" };

    expect(wrapper.vm.getStatusColor(activeBooking)).toBe("green");
    expect(wrapper.vm.getStatusColor(canceledBooking)).toBe("red");
    expect(wrapper.vm.getStatusColor(pastBooking)).toBe("gray");
  });
});
