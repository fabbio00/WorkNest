import { shallowMount } from "@vue/test-utils";
import { describe, it, expect, beforeEach, afterEach, vi } from "vitest";
import BookingsView from "../../views/business/bookings/BusinessBookingsListView.vue"; 

describe("BookingsView", () => {
  let wrapper;
  let ApiServiceMock;

  beforeEach(() => {
    ApiServiceMock = {
      get_list_by_company_booking: vi.fn(() =>
        Promise.resolve({
          data: [
            {
              userResource: { name: "John", surname: "Doe", type: "Employee" },
              status: "active",
              userId: "user1",
              startDate: "2023-05-25T08:00:00Z",
              buildingName: "Building A",
              floorName: "Floor 1",
              workStationName: "WS1",
              workstationCostPerHour: 10,
            },
            {
              userResource: { name: "Jane", surname: "Smith", type: "Employee" },
              status: "inactive",
              userId: "user2",
              startDate: "2023-05-25T08:00:00Z",
              buildingName: "Building B",
              floorName: "Floor 2",
              workStationName: "WS2",
              workstationCostPerHour: 15,
            },
          ],
        })
      ),
      find_user_by_id: vi.fn(() =>
        Promise.resolve({
          data: { companyId: "company1" },
        })
      ),
    };

    wrapper = shallowMount(BookingsView, {
      global: {
        mocks: {
          $ApiService: ApiServiceMock,
        },
      },
    });

    // Mock current date
    vi.setSystemTime(new Date('2023-05-25T08:00:00Z'));
  });

  afterEach(() => {
    wrapper.unmount();
    vi.useRealTimers(); // Reset mock date
  });

  it("should fetch company ID on mount", async () => {
    await wrapper.vm.$nextTick();
    expect(ApiServiceMock.find_user_by_id).toHaveBeenCalled();
    expect(wrapper.vm.companyId).toBe("company1");
  });

  it("should search by name", async () => {
    wrapper.setData({ searchName: "John" });
    await wrapper.vm.initialize_table();
    await wrapper.vm.$nextTick();
    expect(ApiServiceMock.get_list_by_company_booking).toHaveBeenCalledWith(
      "company1", "John", "", null, null
    );
  });

  it("should search by surname", async () => {
    wrapper.setData({ searchSurname: "Doe" });
    await wrapper.vm.initialize_table();
    await wrapper.vm.$nextTick();
    expect(ApiServiceMock.get_list_by_company_booking).toHaveBeenCalledWith(
      "company1", "", "Doe", null, null
    );
  });

  it("should search by start date", async () => {
    wrapper.setData({ startDate: "2023-05-25" });
    await wrapper.vm.initialize_table();
    await wrapper.vm.$nextTick();
    expect(ApiServiceMock.get_list_by_company_booking).toHaveBeenCalledWith(
      "company1", "", "", "2023-05-25", null
    );
  });

  it("should search by end date", async () => {
    wrapper.setData({ endDate: "2023-05-26" });
    await wrapper.vm.initialize_table();
    await wrapper.vm.$nextTick();
    expect(ApiServiceMock.get_list_by_company_booking).toHaveBeenCalledWith(
      "company1", "", "", null, "2023-05-26"
    );
  });

  it("should search by name, surname, and date range", async () => {
    wrapper.setData({ searchName: "Jane", searchSurname: "Smith", startDate: "2023-05-25", endDate: "2023-05-26" });
    await wrapper.vm.initialize_table();
    await wrapper.vm.$nextTick();
    expect(ApiServiceMock.get_list_by_company_booking).toHaveBeenCalledWith(
      "company1", "Jane", "Smith", "2023-05-25", "2023-05-26"
    );
  });

  it("should clear filters and fetch all bookings", async () => {
    wrapper.setData({ searchName: "John", searchSurname: "Doe", startDate: "2023-05-25", endDate: "2023-05-26" });
    await wrapper.vm.clearFilters();
    await wrapper.vm.$nextTick();
    expect(wrapper.vm.searchName).toBe("");
    expect(wrapper.vm.searchSurname).toBe("");
    expect(wrapper.vm.startDate).toBe(null);
    expect(wrapper.vm.endDate).toBe(null);
    expect(ApiServiceMock.get_list_by_company_booking).toHaveBeenCalledWith(
      "company1", "", "", null, null
    );
  });

  it("should format date correctly", () => {
    const formattedDate = wrapper.vm.formatDate("2023-05-25T08:00:00Z");
    expect(formattedDate).toBe("25-05-2023");
  });

  it("should format date for API correctly", () => {
    const formattedDateForApi = wrapper.vm.formatDateForApi("2023-05-25");
    expect(formattedDateForApi).toBe("2023-05-25");
  });

  it("should get correct status color", () => {
    const activeColor = wrapper.vm.getStatusColor({ status: "active", startDate: `2023-05-27T08:00:00Z` });
    const inactiveColor = wrapper.vm.getStatusColor({ status: "active", startDate: "2022-05-25T08:00:00Z" });
    const canceledColor = wrapper.vm.getStatusColor({ status: "canceled", startDate: "2023-05-25T08:00:00Z" });

    expect(activeColor).toBe("green");
    expect(inactiveColor).toBe("gray");
    expect(canceledColor).toBe("red");
  });
});
