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
  });

  afterEach(() => {
    wrapper.unmount();
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
    expect(ApiServiceMock.get_list_by_company_booking).toHaveBeenCalledWith("company1", "John", "");
  });

  it("should search by surname", async () => {
    wrapper.setData({ searchSurname: "Doe" });
    await wrapper.vm.initialize_table();
    await wrapper.vm.$nextTick();
    expect(ApiServiceMock.get_list_by_company_booking).toHaveBeenCalledWith("company1", "", "Doe");
  });

  it("should clear filters and fetch all bookings", async () => {
    wrapper.setData({ searchName: "John", searchSurname: "Doe" });
    await wrapper.vm.clearFilters();
    await wrapper.vm.$nextTick();
    expect(wrapper.vm.searchName).toBe("");
    expect(wrapper.vm.searchSurname).toBe("");
    expect(ApiServiceMock.get_list_by_company_booking).toHaveBeenCalledWith("company1", "", "");
  });
});
