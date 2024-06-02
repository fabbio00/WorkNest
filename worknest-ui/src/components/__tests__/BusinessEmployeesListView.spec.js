import { shallowMount } from "@vue/test-utils";
import { describe, it, expect, beforeEach, afterEach, vi } from "vitest";
import BusinessEmployeesListView from "../../views/business/employees/BusinessEmployeesListView.vue";

describe("BusinessEmployeesListView", () => {
  let wrapper;
  let ApiServiceMock;

  beforeEach(() => {
    ApiServiceMock = {
      get_list_employee: vi.fn(() =>
        Promise.resolve({
          data: [
            {
              id: "user1",
              name: "John",
              surname: "Doe",
              type: "Employee",
              status: "active",
            },
            {
              id: "user2",
              name: "Jane",
              surname: "Smith",
              type: "Employee",
              status: "inactive",
            },
          ],
        }),
      ),
      delete_user: vi.fn(() => Promise.resolve({})),
      find_user_by_id: vi.fn((userId) =>
        Promise.resolve({
          data: { id: userId, email: `${userId}@example.com` },
        }),
      ),
      send_mail: vi.fn(() =>
        Promise.resolve({ data: "Email sent successfully" }),
      ),
      edit_user_type: vi.fn(() => Promise.resolve({})),
    };

    wrapper = shallowMount(BusinessEmployeesListView, {
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

  it("should initialize table with employee data", async () => {
    await wrapper.vm.$nextTick();
    expect(ApiServiceMock.get_list_employee).toHaveBeenCalled();
    await wrapper.vm.$nextTick(); // Aggiungi un'altra chiamata a $nextTick per aspettare che i dati vengano assegnati
    expect(wrapper.vm.employees.length).toBe(2);
    expect(wrapper.vm.employees[0]).toEqual({
      name: "John",
      surname: "Doe",
      type: "Employee",
      status: "active",
      userId: "user1",
    });
  });

  it("should open delete dialog for active employee", () => {
    const employee = {
      id: "user1",
      name: "John",
      surname: "Doe",
      type: "Employee",
      status: "active",
    };
    wrapper.vm.deleteItem(employee);
    expect(wrapper.vm.dialogDelete).toBe(true);
    expect(wrapper.vm.editedItem).toEqual(employee);
  });

  it("should not open delete dialog for inactive employee", () => {
    const employee = {
      id: "user1",
      name: "John",
      surname: "Doe",
      type: "Employee",
      status: "inactive",
    };
    wrapper.vm.deleteItem(employee);
    expect(wrapper.vm.dialogDelete).toBe(false);
  });

  it("should open edit dialog for active employee", () => {
    const employee = {
      id: "user1",
      name: "John",
      surname: "Doe",
      type: "Employee",
      status: "active",
    };
    wrapper.vm.editTypeItem(employee);
    expect(wrapper.vm.dialogEdit).toBe(true);
    expect(wrapper.vm.editedItem).toEqual(employee);
  });

  it("should not open edit dialog for inactive employee", () => {
    const employee = {
      id: "user1",
      name: "John",
      surname: "Doe",
      type: "Employee",
      status: "inactive",
    };
    wrapper.vm.editTypeItem(employee);
    expect(wrapper.vm.dialogEdit).toBe(false);
  });
});
