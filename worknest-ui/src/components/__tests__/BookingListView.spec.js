import { shallowMount } from '@vue/test-utils';
import { describe, it, expect, beforeEach, afterEach, vi } from 'vitest';
import BookingListView from '../../views/BookingListView.vue'; 

describe('BookingListView', () => {
  let wrapper;

  beforeEach(() => {
    const ApiServiceMock = {
      get_list_booking: vi.fn(() => Promise.resolve({
        data: [
          {
            startDate: '2024-04-06T10:15:30+01:00',
            checkIn: null,
            checkOut: null,
            workStationId: 'workStationId1',
            status: 'active'
          },
        
        ]
      }))
    };

    wrapper = shallowMount(BookingListView, {
      global: {
        mocks: {
          $ApiService: ApiServiceMock
        }
      }
    });
  });

  afterEach(() => {
    wrapper.unmount();
  });

  it('should fetch bookings and workstations', async () => {

    await wrapper.vm.initialize_table();

    const expectedBookings = [
      {
        startDate: '2024-04-06T10:15:30+01:00',
        checkIn: null,
        checkOut: null,
        workStationId: 'workStationId1',
        status: 'active'
      }
    ];

    expect(wrapper.vm.$ApiService.get_list_booking).toHaveBeenCalled();

  });
});
