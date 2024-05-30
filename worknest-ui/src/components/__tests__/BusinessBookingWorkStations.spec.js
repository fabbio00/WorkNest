import { shallowMount } from "@vue/test-utils";
import { describe, it, expect, beforeEach, afterEach, vi } from "vitest";
import BusinessBookingWorkStations from "../../views/business/bookings/BusinessBookingWorkStations.vue";

describe("BusinessBookingWorkStations", () => {
  let wrapper;

  beforeEach(() => {
    wrapper = shallowMount(BusinessBookingWorkStations, {
      global: {
        mocks: {
          $ApiService: {
            find_user_by_id: vi.fn(() => Promise.resolve({ data: { id: "user1", email: "john@example.com" } })),
            find_desk_by_id: vi.fn(),
            find_occupied_desks: vi.fn(() => Promise.resolve({ data: [] })),
            create_booking: vi.fn(),
            send_mail: vi.fn(),
            get_list_employee: vi.fn(() => Promise.resolve({ data: [] })),
          },
        },
      },
    });
    wrapper.vm.bookingList = [];
  });

  afterEach(() => {
    wrapper.unmount();
  });

  it("formatDate should format the date correctly", () => {
    const date = new Date("2024-04-07T12:30:00");
    const formattedDate = wrapper.vm.formatDate(date);
    expect(formattedDate).toBe("2024-04-07");
  });

  it("findOccupiedDesks method works correctly", async () => {
    const findUserByIdSpy = vi.fn(() =>
      Promise.resolve({ data: { companyId: "company1" } }),
    );
    wrapper.vm.$ApiService.find_user_by_id = findUserByIdSpy;

    wrapper.vm.userId = "user1";
    wrapper.vm.selectedDate = new Date("2024-04-07T12:30:00");

    await wrapper.vm.findOccupiedDesks();

    expect(findUserByIdSpy).toHaveBeenCalledWith("user1");
  });

  it("createBooking method creates a booking successfully", async () => {
    const createBookingBusinessSpy = vi.fn(() =>
      Promise.resolve({ data: { bookingBusinessId: "booking1" } }),
    );
    wrapper.vm.$ApiService.create_booking_business = createBookingBusinessSpy;

    wrapper.vm.bookingList = [
      {
        booking: {
          startDate: "2024-04-07T09:00:00Z",
          endDate: "2024-04-07T18:00:00Z",
          workStationId: "ws1",
        },
        user: {
          email: "john@example.com",
        },
      },
    ];
    wrapper.vm.userId = "user1";
    wrapper.vm.booking = {
      startDate: "2024-04-07T09:00:00Z",
    };

    await wrapper.vm.createBooking();

    expect(createBookingBusinessSpy).toHaveBeenCalledWith({
      bookingDate: "2024-04-07T09:00:00Z",
      userId: "user1",
    });
  });

  it("deleteItem method deletes an item successfully", () => {
    const item = {
      user: { id: "user1" },
      booking: { userId: "user1", workStationId: "ws1" },
      workstation: { type: "desk" },
    };

    wrapper.vm.bookingList = [item];
    wrapper.vm.employeeWithBooking = [item];
    wrapper.vm.meetingRoomList = [];
    wrapper.vm.savedUsersMeetingRoomList = [];

    wrapper.vm.deleteItem(item);

    expect(wrapper.vm.bookingList).toHaveLength(0);
    expect(wrapper.vm.employeeWithBooking).toHaveLength(0);
  });



});