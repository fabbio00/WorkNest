import { shallowMount } from '@vue/test-utils';
import { describe, it, expect, beforeEach, afterEach, vi } from 'vitest';
import BookingWorkStation from '../../views/BookingWorkStation.vue';

describe('BookingWorkStation', () => {
  let wrapper;

  beforeEach(() => {
    wrapper = shallowMount(BookingWorkStation, {
      global: {
        mocks: {
          $ApiService: {
            find_user_by_id: vi.fn(),
            find_desk_by_id: vi.fn(),
            find_occupied_desks: vi.fn(),
            create_booking: vi.fn(),
            send_mail: vi.fn(),
          }
        }
      }
    });
  });

  afterEach(() => {
    wrapper.unmount();
  });

  it('formatDate should format the date correctly', () => {
    const date = new Date('2024-04-07T12:30:00');
    const formattedDate = wrapper.vm.formatDate(date);
    expect(formattedDate).toBe('2024-04-07');
  });

  it('createBooking method creates a booking successfully', async () => {
    
    const createBookingSpy = vi.fn(() => Promise.resolve('Booking created successfully'));
    wrapper.vm.$ApiService.create_booking = createBookingSpy;

    wrapper.vm.booking = {
        startDate: '2024-04-07T09:00:00Z', 
        endDate: '2024-04-07T18:00:00Z',  
        user: {
          name: 'John',
          surname: 'Doe',
          email: 'john@example.com',
          password: 'test123',
          taxCode: 'taxcode',
          companyCode: 'companyCode',
          type: "Employee",
          barrerFreeFlag: false,
        },
        workStation: {
          name: 'Workstation Name',
          pricePerH: 10.5,
          equipment: 'Equipment List',
          type: 'Type of Workstation',
          floor: 1
        }
    };

    await wrapper.vm.createBooking();

    expect(createBookingSpy).toHaveBeenCalledWith(wrapper.vm.booking);

  });
  
});


