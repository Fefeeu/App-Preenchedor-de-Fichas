import type { SheetResume } from "./sheet";

export interface ApiResponse {
  success: boolean;
  message: string;
  data: SheetResume[];
}