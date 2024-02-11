import { TestBed } from '@angular/core/testing';

import { TopicService } from './topic.service';
import { HttpClientModule } from '@angular/common/http';

describe('TopicService', () => {
  let service: TopicService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientModule
      ]
    });
    service = TestBed.inject(TopicService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
