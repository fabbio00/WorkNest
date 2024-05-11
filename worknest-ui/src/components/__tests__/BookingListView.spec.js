import { shallowMount } from "@vue/test-utils";
import { describe, it, expect, beforeEach, afterEach, vi } from "vitest";
import BookingListView from "../../views/bookings/BookingListView.vue";

describe("BookingListView", () => {
  let wrapper;

  beforeEach(() => {
    const ApiServiceMock = {
      get_list_booking: vi.fn(() =>
        Promise.resolve({
          data: [
            {
              startDate: "2024-04-06T10:15:30+01:00",
              checkIn: null,
              checkOut: null,
              workStationId: "workStationId1",
              status: "active",
            },
          ],
        }),
      ),
      delete_booking: vi.fn(() => Promise.resolve({})),
      find_user_by_id: vi.fn(() =>
        Promise.resolve({ data: { email: "test@example.com" } }),
      ),
      send_mail: vi.fn(() =>
        Promise.resolve({ data: "Email sent successfully" }),
      ),
    };

    wrapper = shallowMount(BookingListView, {
      global: {
        mocks: {
          $ApiService: ApiServiceMock,
        },
      },
    });
  });

  afterEach(() => {
    wrapper.unmount();
  });

  it("should fetch bookings and workstations", async () => {
    await wrapper.vm.initialize_table();

    const expectedBookings = [
      {
        startDate: "2024-04-06T10:15:30+01:00",
        checkIn: null,
        checkOut: null,
        workStationId: "workStationId1",
        status: "active",
      },
    ];

    expect(wrapper.vm.$ApiService.get_list_booking).toHaveBeenCalled();
  });

  it("calls deleteItem and deleteItemConfirm when delete button is clicked", async () => {
    const bookingItem = {
      startDate: "2024-04-06",
      checkIn: "-",
      checkOut: "-",
      workStation: "Workspace 1",
      status: "active",
      bookingId: "bookingId1",
    };

    wrapper.setData({ editedItem: bookingItem, dialogDelete: true });

    await wrapper.vm.deleteItemConfirm();

    expect(wrapper.vm.$ApiService.delete_booking).toHaveBeenCalledWith(
      "bookingId1",
    );
  });
});
